package org.letscareer.letscareer.domain.pg.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.pg.dto.response.CardPromotionResponseDto;
import org.letscareer.letscareer.domain.pg.service.GetCardPromotionsService;
import org.letscareer.letscareer.global.common.entity.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/pg")
@RestController
public class PgV1Controller {
    private final GetCardPromotionsService getCardPromotionsService;

    @Operation(summary = "토스페이먼츠 카드 프로모션 조회")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CardPromotionResponseDto.class)))
    @GetMapping("/promotions/card")
    private ResponseEntity<SuccessResponse<?>> getCardPromotions() {
        final CardPromotionResponseDto responseDto = getCardPromotionsService.execute();
        return SuccessResponse.ok(responseDto);
    }
}
