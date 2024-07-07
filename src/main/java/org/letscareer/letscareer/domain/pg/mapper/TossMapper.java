package org.letscareer.letscareer.domain.pg.mapper;

import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.pg.dto.request.TossPaymentsRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TossMapper {

    public TossPaymentsRequestDto toTossPaymentsRequestDto(CreatePaymentRequestDto paymentRequestDto) {
        return TossPaymentsRequestDto.of(paymentRequestDto);
    }
}
