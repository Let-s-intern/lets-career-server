package org.letscareer.letscareer.domain.pg.provider;

import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PgProvider {
    void requestPayments(CreatePaymentRequestDto paymentRequestDto);

    TossPaymentsResponseDto requestPaymentDetail();
}
