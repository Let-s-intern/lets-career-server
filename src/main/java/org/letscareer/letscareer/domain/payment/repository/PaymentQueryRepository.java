package org.letscareer.letscareer.domain.payment.repository;

import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;

import java.util.Optional;

public interface PaymentQueryRepository {
    Optional<Payment> findPaymentByApplicationId(Long applicationId);

    long countCouponAppliedTime(Long userId, Long couponId);

    Optional<PaymentDetailVo> findPaymentDetailVoByPaymentId(Long paymentId);

    Optional<Payment> findPaymentByChallengeIdAndUserId(Long challengeId, Long userId);
}
