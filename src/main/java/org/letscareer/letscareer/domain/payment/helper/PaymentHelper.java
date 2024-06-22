package org.letscareer.letscareer.domain.payment.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.repository.PaymentRepository;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.payment.error.PaymentErrorCode.PAYMENT_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class PaymentHelper {
    private final PaymentRepository paymentRepository;

    public Payment createPaymentAndSave(Application application, Coupon coupon, Price price) {
        int finalPrice = calculateFinalPrice(price, coupon);
        Payment newPayment = Payment.createPayment(finalPrice, coupon, application);
        return paymentRepository.save(newPayment);
    }

    public Payment findPaymentByIdOrThrow(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException(PAYMENT_NOT_FOUND));
    }

    public Payment findPaymentByApplicationIdOrThrow(Long applicationId) {
        return paymentRepository.findPaymentByApplicationId(applicationId)
                .orElseThrow(() -> new EntityNotFoundException(PAYMENT_NOT_FOUND));
    }

    public long countCouponAppliedTime(Long userId, Long couponId) {
        return paymentRepository.countCouponAppliedTime(userId, couponId);
    }

    private int calculateFinalPrice(Price price, Coupon coupon) {
        int finalPrice = price.getPrice() - price.getDiscount();
        if (coupon != null) finalPrice -= coupon.getDiscount();
        return finalPrice;
    }

    public PaymentDetailVo findPaymentDetailVoByPaymentId(Long paymentId) {
        return paymentRepository.findPaymentDetailVoByPaymentId(paymentId)
                .orElseThrow(() -> new EntityNotFoundException(PAYMENT_NOT_FOUND));
    }
}
