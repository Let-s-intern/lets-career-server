package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentDetailResponseDto(
        PaymentDetailVo paymentInfo
) {
    public static GetPaymentDetailResponseDto of(PaymentDetailVo paymentInfo) {
        return GetPaymentDetailResponseDto.builder()
                .paymentInfo(paymentInfo)
                .build();
    }
}
