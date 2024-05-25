package org.letscareer.letscareer.domain.payment.repository;

import java.util.Optional;

public interface PaymentQueryRepository {
    Optional<Integer> findCouponRemainTime(Long userId, Long couponId);
}
