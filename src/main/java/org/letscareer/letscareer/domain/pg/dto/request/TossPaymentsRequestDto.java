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
    public static TossPaymentsRequestDto of(CreatePaymentRequestDto paymentRequestDto) {
        return TossPaymentsRequestDto.builder()
                .paymentKey(paymentRequestDto.paymentKey())
                .orderId(paymentRequestDto.orderId())
                .amount(paymentRequestDto.amount())
                .build();
    }
}
