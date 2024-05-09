package org.letscareer.letscareer.domain.price.dto.request;

import org.letscareer.letscareer.domain.price.type.LivePriceType;

public record CreateLivePriceRequestDto(
        CreatePriceRequestDto priceInfo,
        LivePriceType livePriceType
) {
}
