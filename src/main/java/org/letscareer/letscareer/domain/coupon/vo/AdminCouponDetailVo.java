package org.letscareer.letscareer.domain.coupon.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.coupon.type.CouponType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
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
    public AdminCouponDetailVo(Coupon coupon) {
        this(coupon.getId(),
                coupon.getCouponType(),
                coupon.getCouponProgramList().stream().map(couponProgram -> couponProgram.getCouponProgramType()).collect(Collectors.toList()),
                coupon.getName(),
                coupon.getCode(),
                coupon.getDiscount(),
                coupon.getTime(),
                coupon.getStartDate(),
                coupon.getEndDate(),
                coupon.getCreateDate());
    }
}
