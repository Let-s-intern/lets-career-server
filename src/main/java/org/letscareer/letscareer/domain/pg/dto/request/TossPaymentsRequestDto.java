package org.letscareer.letscareer.domain.pg.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;

@Builder(access = AccessLevel.PRIVATE)
public record TossPaymentsRequestDto(
        String paymentKey,
        String orderId,
        String amount
) {
    public static TossPaymentsRequestDto of(String paymentKey, String orderId, String amount) {
        return TossPaymentsRequestDto.builder()
                .paymentKey(paymentKey)
                .orderId(orderId)
                .amount(amount)
                .build();
    }
}
