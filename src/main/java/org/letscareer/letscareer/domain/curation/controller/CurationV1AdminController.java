package org.letscareer.letscareer.domain.curation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.response.CouponApplyResponseDto;
import org.letscareer.letscareer.domain.curation.dto.request.CreateCurationRequestDto;
import org.letscareer.letscareer.domain.curation.service.CurationService;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/curation")
@RestController
public class CurationV1AdminController {
    private final CurationService curationService;

    @Operation(summary = "큐레이션 생성", responses = {@ApiResponse(responseCode = "201", useReturnTypeSchema = true)})
    @PostMapping("/{locationType}")
    public ResponseEntity<SuccessResponse<?>> createCuration(@PathVariable final CurationLocationType locationType,
                                                             @RequestBody @Valid final CreateCurationRequestDto requestDto) {
        curationService.createCuration(locationType, requestDto);
        return SuccessResponse.created(null);
    }

}
