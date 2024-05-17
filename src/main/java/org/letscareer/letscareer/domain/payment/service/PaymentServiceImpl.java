package org.letscareer.letscareer.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;

    @Override
    public void updatePayment(Long paymentId, UpdatePaymentRequestDto updatePaymentRequestDto) {
        Payment payment = paymentHelper.findPaymentByIdOrThrow(paymentId);
        payment.updatePayment(updatePaymentRequestDto);
    }
}
