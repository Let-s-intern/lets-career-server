package org.letscareer.letscareer.domain.payment.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.payment.vo.PaymentDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.price.entity.QPrice.price1;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentQueryRepositoryImpl implements PaymentQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Payment> findPaymentByApplicationId(Long applicationId) {
        return Optional.ofNullable(queryFactory
                .select(payment)
                .from(payment)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchOne()
        );
    }

    @Override
    public long countCouponAppliedTime(Long userId, Long couponId) {
        return queryFactory
                .select(payment.id.countDistinct())
                .from(payment)
                .where(
                        eqUserId(userId),
                        eqCouponId(couponId)
                )
                .leftJoin(payment.application.user, user)
                .leftJoin(payment.coupon, coupon)
                .fetchFirst();
    }

    @Override
    public Optional<PaymentDetailVo> findPaymentDetailVoByPaymentId(Long paymentId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(PaymentDetailVo.class,
                            payment.id,
                            payment.finalPrice,
                            coupon.discount))
                        .from(payment)
                        .leftJoin(payment.coupon, coupon)
                        .where(
                            eqPaymentId(paymentId)
                        )
                        .orderBy(payment.id.desc())
                        .fetchFirst());
    }

    private BooleanExpression eqPaymentId(Long paymentId) {
        return paymentId != null ? payment.id.eq(paymentId) : null;
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? payment.application.id.eq(applicationId) : null;
    }

    private Predicate eqCouponId(Long couponId) {
        return couponId != null ? coupon.id.eq(couponId) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }
}
