package org.letscareer.letscareer.domain.missiontemplate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.CreateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.request.UpdateMissionTemplateRequestDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.response.MissionTemplateAdminListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.dto.response.MissionTemplateAdminSimpleListResponseDto;
import org.letscareer.letscareer.domain.missiontemplate.service.MissionTemplateService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission-template")
@RestController
public class MissionTemplateV1Controller {
    private final MissionTemplateService missionTemplateService;

    @Operation(summary = "[어드민] 미션 템플릿 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createMissionTemplate(@RequestBody @Valid final CreateMissionTemplateRequestDto createMissionTemplateRequestDto) {
        missionTemplateService.createMissionTemplate(createMissionTemplateRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 미션 템플릿 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MissionTemplateAdminListResponseDto.class)))
    })
    @GetMapping("/admin")
    public ResponseEntity<SuccessResponse<?>> getMissionTemplatesForAdmin() {
        MissionTemplateAdminListResponseDto responseDto = missionTemplateService.getMissionTemplatesForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 미션 템플릿 간단 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MissionTemplateAdminSimpleListResponseDto.class)))
    })
    @GetMapping("/admin/simple")
    public ResponseEntity<SuccessResponse<?>> getSimpleMissionTemplatesForAdmin() {
        MissionTemplateAdminSimpleListResponseDto responseDto = missionTemplateService.getSimpleMissionTemplatesForAdmin();
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 미션 템플릿 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{missionTemplateId}")
    public ResponseEntity<SuccessResponse<?>> updateMissionTemplate(@PathVariable final Long missionTemplateId,
                                                                    @RequestBody final UpdateMissionTemplateRequestDto updateMissionTemplateRequestDto) {
        missionTemplateService.updateMissionTemplate(missionTemplateId, updateMissionTemplateRequestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "[어드민] 미션 템플릿 삭제", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @DeleteMapping("/{missionTemplateId}")
    public ResponseEntity<SuccessResponse<?>> deleteMissionTemplate(@PathVariable final Long missionTemplateId) {
        missionTemplateService.deleteMissionTemplate(missionTemplateId);
        return SuccessResponse.ok(null);
    }
}
