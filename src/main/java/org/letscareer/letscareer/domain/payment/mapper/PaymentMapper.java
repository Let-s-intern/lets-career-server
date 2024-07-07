package org.letscareer.letscareer.domain.payment.mapper;

import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.price.vo.PriceDetailVo;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public GetPaymentDetailResponseDto toGetPaymentDetailResponseDto(PriceDetailVo priceInfo, PaymentDetailVo paymentInfo, TossPaymentsResponseDto tossInfo) {
        return GetPaymentDetailResponseDto.of(priceInfo, paymentInfo, tossInfo);
    }
}
