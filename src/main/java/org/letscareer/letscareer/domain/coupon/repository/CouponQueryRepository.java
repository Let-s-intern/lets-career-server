package org.letscareer.letscareer.domain.coupon.repository;

import org.letscareer.letscareer.domain.coupon.vo.AdminCouponDetailVo;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;

import java.util.List;
import java.util.Optional;

public interface CouponQueryRepository {
    List<AdminCouponVo> findAllAdminCouponVos();

    Optional<AdminCouponDetailVo> findAdminCouponDetailVo(Long couponId);
}
