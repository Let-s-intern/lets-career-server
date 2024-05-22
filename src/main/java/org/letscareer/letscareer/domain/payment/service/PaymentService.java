package org.letscareer.letscareer.domain.payment.service;

import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void updatePayment(Long paymentId, UpdatePaymentRequestDto updatePaymentRequestDto);
}
