package org.letscareer.letscareer.domain.pg.dto.response;

import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.toss.CardDiscountInfo;
import org.letscareer.letscareer.domain.pg.dto.response.toss.CardInterestFreeInfo;

import java.util.List;

@Builder
public record CardPromotionResponseDto(
        List<CardDiscountInfo> discountCards,
        List<CardInterestFreeInfo> interestFreeCards
) {
}
