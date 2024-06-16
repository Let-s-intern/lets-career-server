package org.letscareer.letscareer.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.service.MissionService;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
@RestController
public class MissionV1Controller {
    private final MissionService missionService;

    @Operation(summary = "[어드민] 미션 생성", responses = {
            @ApiResponse(responseCode = "201", useReturnTypeSchema = true)
    })
    @PostMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> createMission(@PathVariable(name = "id") final Long challengeId,
                                                            @RequestBody @Valid final CreateMissionRequestDto createMissionRequestDto) {
        missionService.createMission(challengeId, createMissionRequestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "[어드민] 챌린지 1개의 미션 전체 목록", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = MissionAdminListResponseDto.class)))
    })
    @ApiErrorCode({SwaggerEnum.MISSION_NOT_FOUND})
    @GetMapping("/{id}/admin")
    public ResponseEntity<SuccessResponse<?>> getMissionsForAdmin(@PathVariable(name = "id") Long challengeId) {
        MissionAdminListResponseDto responseDto = missionService.getMissionsForAdmin(challengeId);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "[어드민] 미션 수정", responses = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateMission(@PathVariable(name = "id") final Long missionId,
                                                            @RequestBody final UpdateMissionRequestDto updateMissionRequestDto) {
        missionService.updateMission(missionId, updateMissionRequestDto);
        return SuccessResponse.ok(null);
    }
}
