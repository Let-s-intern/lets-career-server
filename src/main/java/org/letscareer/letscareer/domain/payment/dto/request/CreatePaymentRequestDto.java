package org.letscareer.letscareer.domain.payment.dto.request;

public record CreatePaymentRequestDto(
        Long couponId,
        Long priceId,
        String paymentKey,
        String orderId,
        String amount
) {
}
