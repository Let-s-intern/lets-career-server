package org.letscareer.letscareer.domain.payment.repository;

import org.letscareer.letscareer.domain.payment.entity.Payment;

import java.util.Optional;

public interface PaymentQueryRepository {
    Optional<Payment> findPaymentByApplicationId(Long applicationId);
    long countCouponAppliedTime(Long userId, Long couponId);
}
