package org.letscareer.letscareer.domain.coupon.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.coupon.type.CouponType;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AdminCouponDetailVo(
        CouponType couponType,
        List<CouponProgramType> couponProgramList,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createDate
) {
}
