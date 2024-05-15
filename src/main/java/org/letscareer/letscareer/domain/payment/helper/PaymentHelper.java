package org.letscareer.letscareer.domain.payment.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.repository.PaymentRepository;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class PaymentHelper {
    private final PaymentRepository paymentRepository;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;

    public Payment createPaymentAndSave(Application application,
                                     CreatePaymentRequestDto paymentInfo) {
        Coupon coupon = couponHelper.findCouponByIdOrThrow(paymentInfo.couponId());
        Price price = priceHelper.findPriceByIdOrThrow(paymentInfo.priceId());
        Payment newPayment = Payment.createPayment(coupon, application, price);
        return paymentRepository.save(newPayment);
    }
}
