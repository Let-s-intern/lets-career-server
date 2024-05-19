package org.letscareer.letscareer.domain.coupon.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponDetailVo;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;

@RequiredArgsConstructor
public class CouponQueryRepositoryImpl implements CouponQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminCouponVo> findAllAdminCouponVos() {
        return queryFactory
                .select(Projections.constructor(AdminCouponVo.class,
                        coupon.id,
                        coupon.couponType,
                        coupon.name,
                        coupon.code,
                        coupon.startDate,
                        coupon.endDate))
                .from(coupon)
                .orderBy(coupon.id.desc())
                .fetch();
    }

    @Override
    public Optional<AdminCouponDetailVo> findAdminCouponDetailVo(Long couponId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(AdminCouponDetailVo.class,
                        coupon.id,
                        coupon.couponType,
                        coupon.couponProgramList,
                        coupon.name,
                        coupon.code,
                        coupon.discount,
                        coupon.time,
                        coupon.startDate,
                        coupon.endDate,
                        coupon.createDate))
                .from(coupon)
                .where(
                        eqCouponId(couponId)
                )
                .fetchOne());
    }

    private BooleanExpression eqCouponId(Long couponId) {
        return couponId != null ? coupon.id.eq(couponId) : null;
    }
}
