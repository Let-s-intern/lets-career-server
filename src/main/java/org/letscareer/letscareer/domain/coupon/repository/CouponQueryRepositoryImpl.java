package org.letscareer.letscareer.domain.coupon.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;

import java.util.List;

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
}
