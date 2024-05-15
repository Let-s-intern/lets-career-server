package org.letscareer.letscareer.domain.payment.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.domain.price.helper.PriceHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentHelper paymentHelper;
    private final CouponHelper couponHelper;
    private final PriceHelper priceHelper;

    public Payment createPaymentAndSave(Application application,
                                        CreatePaymentRequestDto paymentInfo) {
        Coupon coupon = couponHelper.findCouponByIdOrNull(paymentInfo.couponId());
        Price price = priceHelper.findPriceByIdOrThrow(paymentInfo.priceId());
        Payment newPayment = Payment.createPayment(paymentInfo, coupon, application, price);
        return paymentHelper.savePayment(newPayment);
    }
}
