package org.letscareer.letscareer.domain.coupon.dto.request;

import org.letscareer.letscareer.domain.coupon.type.CouponType;

import java.time.LocalDateTime;
import java.util.List;

public record CreateCouponRequestDto(
        CouponType couponType,
        List<CreateCouponProgramRequestDto> programTypeList,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
