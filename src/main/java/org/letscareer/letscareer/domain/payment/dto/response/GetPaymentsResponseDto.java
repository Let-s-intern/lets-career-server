package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentsResponseDto(
        List<GetPaymentResponseDto> payments
) {
    public static GetPaymentsResponseDto of(List<GetPaymentResponseDto> payments) {
        return GetPaymentsResponseDto.builder()
                .payments(payments)
                .build();
    }
}
