package org.letscareer.letscareer.domain.pg.dto.request;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record TossPaymentsCancelRequestDto(
        String cancelReason
) {
    public static TossPaymentsCancelRequestDto of(String cancelReason) {
        return TossPaymentsCancelRequestDto.builder()
                .cancelReason(cancelReason)
                .build();
    }
}
