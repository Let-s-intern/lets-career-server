package org.letscareer.letscareer.domain.payment.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentQueryRepositoryImpl implements PaymentQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<Integer> findCouponRemainTime(Long userId, Long couponId) {
        return Optional.ofNullable(
                queryFactory
                        .select(payment.couponRemainTime)
                        .from(payment)
                        .where(
                                eqUserId(userId),
                                eqCouponId(couponId)
                        )
                        .leftJoin(payment.application.user, user)
                        .leftJoin(payment.coupon, coupon)
                        .orderBy(payment.id.desc())
                        .fetchFirst()
        );
    }

    private Predicate eqCouponId(Long couponId) {
        return couponId != null ? coupon.id.eq(couponId) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }
}
