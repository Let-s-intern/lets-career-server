package org.letscareer.letscareer.domain.coupon.vo;

import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.coupon.type.CouponType;

import java.time.LocalDateTime;
import java.util.List;

public record AdminCouponDetailVo(
        Long id,
        CouponType couponType,
        List<CouponProgramType> couponProgramTypeList,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createDate
) {
}
