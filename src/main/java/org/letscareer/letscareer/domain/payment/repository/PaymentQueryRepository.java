package org.letscareer.letscareer.domain.payment.repository;

public interface PaymentQueryRepository {
    long countCouponAppliedTime(Long userId, Long couponId);
}
