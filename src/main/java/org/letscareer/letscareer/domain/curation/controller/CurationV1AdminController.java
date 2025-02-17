package org.letscareer.letscareer.domain.curation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.request.UpdateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.dto.response.GetAdminCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.service.CurationService;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.global.common.annotation.ApiErrorCode;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.letscareer.letscareer.global.common.entity.SwaggerEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/curation")
@RestController
public class CurationV1AdminController {
    private final CurationService curationService;

    @Operation(summary = "큐레이션 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAdminCurationsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getCurations(@RequestParam(required = false) final CurationLocationType locationType) {
        GetAdminCurationsResponseDto responseDto = curationService.getAdminCurations(locationType);
        return SuccessResponse.ok(responseDto);
    }

    @Operation(summary = "큐레이션 생성", responses = {@ApiResponse(responseCode = "201", useReturnTypeSchema = true)})
    @PostMapping("/{locationType}")
    public ResponseEntity<SuccessResponse<?>> createCuration(@PathVariable final CurationLocationType locationType,
                                                             @RequestBody @Valid final CreateCurationRequestDto requestDto) {
        curationService.createCuration(locationType, requestDto);
        return SuccessResponse.created(null);
    }

    @Operation(summary = "큐레이션 수정", responses = {@ApiResponse(responseCode = "200", useReturnTypeSchema = true)})
    @ApiErrorCode(SwaggerEnum.CURATION_NOT_FOUND)
    @PatchMapping("/{curationId}")
    public ResponseEntity<SuccessResponse<?>> updateCuration(@PathVariable final Long curationId,
                                                             @RequestBody final UpdateCurationRequestDto requestDto) {
        curationService.updateCuration(curationId, requestDto);
        return SuccessResponse.ok(null);
    }

    @Operation(summary = "큐레이션 삭제", responses = {@ApiResponse(responseCode = "200", useReturnTypeSchema = true)})
    @ApiErrorCode(SwaggerEnum.CURATION_NOT_FOUND)
    @DeleteMapping("/{curationId}")
    public ResponseEntity<SuccessResponse<?>> deleteCuration(@PathVariable final Long curationId) {
        curationService.deleteCuration(curationId);
        return SuccessResponse.ok(null);
    }

}
