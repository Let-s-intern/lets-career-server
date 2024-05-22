package org.letscareer.letscareer.domain.coupon.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.coupon.type.CouponType;

import java.time.LocalDateTime;

@Builder
public record AdminCouponVo(
        Long id,
        CouponType couponType,
        String name,
        String code,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
