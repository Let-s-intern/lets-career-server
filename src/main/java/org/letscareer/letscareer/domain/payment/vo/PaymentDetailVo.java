package org.letscareer.letscareer.domain.payment.vo;

import lombok.Builder;

@Builder
public record PaymentDetailVo(
        Long id,
        Integer finalPrice,
        Integer couponDiscount
) {
}
