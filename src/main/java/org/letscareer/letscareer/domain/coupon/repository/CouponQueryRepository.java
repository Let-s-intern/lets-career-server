package org.letscareer.letscareer.domain.coupon.repository;

import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;

import java.util.List;

public interface CouponQueryRepository {
    List<AdminCouponVo> findAllAdminCouponVos();
}
