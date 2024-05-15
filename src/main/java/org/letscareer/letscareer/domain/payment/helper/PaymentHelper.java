package org.letscareer.letscareer.domain.payment.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.repository.PaymentRepository;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class PaymentHelper {
    private final PaymentRepository paymentRepository;

    public Payment createPaymentAndSave(CreatePaymentRequestDto paymentInfo,
                                        Application application,
                                        Coupon coupon,
                                        Price price) {
        Payment newPayment = Payment.createPayment(paymentInfo, coupon, application, price);
        return paymentRepository.save(newPayment);
    }
}
