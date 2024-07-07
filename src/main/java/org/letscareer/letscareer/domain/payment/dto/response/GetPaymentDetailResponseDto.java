package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentDetailResponseDto(
        PriceDetailVo priceInfo,
        PaymentDetailVo paymentInfo,
        TossPaymentsResponseDto tossInfo
) {
    public static GetPaymentDetailResponseDto of(PriceDetailVo priceInfo,
                                                 PaymentDetailVo paymentInfo,
                                                 TossPaymentsResponseDto tossInfo) {
        return GetPaymentDetailResponseDto.builder()
                .priceInfo(priceInfo)
                .paymentInfo(paymentInfo)
                .tossInfo(tossInfo)
                .build();
    }
}
