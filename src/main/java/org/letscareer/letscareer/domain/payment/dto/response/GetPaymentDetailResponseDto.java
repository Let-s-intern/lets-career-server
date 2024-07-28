package org.letscareer.letscareer.domain.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.letscareer.letscareer.domain.program.vo.ProgramSimpleVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetPaymentDetailResponseDto(
        ProgramSimpleVo programInfo,
        PriceDetailVo priceInfo,
        PaymentDetailVo paymentInfo,
        TossPaymentsResponseDto tossInfo
) {
    public static GetPaymentDetailResponseDto of(ProgramSimpleVo programSimpleVo,
                                                 PriceDetailVo priceInfo,
                                                 PaymentDetailVo paymentInfo,
                                                 TossPaymentsResponseDto tossInfo) {
        return GetPaymentDetailResponseDto.builder()
                .programInfo(programSimpleVo)
                .priceInfo(priceInfo)
                .paymentInfo(paymentInfo)
                .tossInfo(tossInfo)
                .build();
    }
}
