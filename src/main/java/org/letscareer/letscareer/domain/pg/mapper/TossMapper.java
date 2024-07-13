package org.letscareer.letscareer.domain.pg.mapper;

import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.type.RefundType;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsCancelRequestDto;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TossMapper {

    public TossPaymentsRequestDto toTossPaymentsRequestDto(CreatePaymentRequestDto paymentRequestDto) {
        return TossPaymentsRequestDto.of(paymentRequestDto);
    }

    public TossPaymentsCancelRequestDto toTossPaymentsCancelRequestDto(RefundType refundType, String cancelReason, Integer cancelAmount) {
        return TossPaymentsCancelRequestDto.of(refundType, cancelReason, cancelAmount);
    }
}
