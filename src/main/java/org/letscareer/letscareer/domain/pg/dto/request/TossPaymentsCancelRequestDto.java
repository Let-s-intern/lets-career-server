package org.letscareer.letscareer.domain.pg.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.type.RefundType;

import java.util.Objects;

@Builder(access = AccessLevel.PRIVATE)
public record TossPaymentsCancelRequestDto(
        String cancelReason,
        Integer cancelAmount
) {
    public static TossPaymentsCancelRequestDto of(RefundType refundType, String cancelReason, Integer cancelAmount) {
        if(!Objects.isNull(refundType) && refundType.equals(RefundType.ALL)) {
            return TossPaymentsCancelRequestDto.builder()
                    .cancelReason(cancelReason)
                    .build();
        }
        return TossPaymentsCancelRequestDto.builder()
                .cancelReason(cancelReason)
                .cancelAmount(cancelAmount)
                .build();
    }
}
