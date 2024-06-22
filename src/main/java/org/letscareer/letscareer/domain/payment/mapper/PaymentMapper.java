package org.letscareer.letscareer.domain.payment.mapper;

import org.letscareer.letscareer.domain.payment.dto.response.GetPaymentDetailResponseDto;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public GetPaymentDetailResponseDto toGetPaymentDetailResponseDto(PaymentDetailVo paymentDetailVo) {
        return GetPaymentDetailResponseDto.of(paymentDetailVo);
    }
}
