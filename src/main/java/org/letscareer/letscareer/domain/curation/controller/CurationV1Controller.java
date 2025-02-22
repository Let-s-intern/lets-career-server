package org.letscareer.letscareer.domain.curation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.dto.response.GetCurationsResponseDto;
import org.letscareer.letscareer.domain.curation.service.CurationService;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/curation")
@RestController
public class CurationV1Controller {
    private final CurationService curationService;

    @Operation(summary = "큐레이션 목록 조회", responses = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetCurationsResponseDto.class)))
    })
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getCurations(@RequestParam(required = false) final CurationLocationType locationType) {
        GetCurationsResponseDto responseDto = curationService.getCurations(locationType);
        return SuccessResponse.ok(responseDto);
    }
}
