package org.letscareer.letscareer.domain.pg.provider;

import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface PgProvider {
    void requestPayments(CreatePaymentRequestDto paymentRequestDto);
}
