package org.letscareer.letscareer.domain.payment.service;

import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Payment createPaymentAndSave(Application application, CreatePaymentRequestDto paymentInfo);
}
