package org.letscareer.letscareer.domain.payment.dto.request;

public record CreatePaymentRequestDto(
        Integer finalPrice,
        Integer couponRemainTime,
        Long couponId
) {
}
