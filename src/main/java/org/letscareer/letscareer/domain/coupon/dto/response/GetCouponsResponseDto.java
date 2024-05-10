package org.letscareer.letscareer.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetCouponsResponseDto(
        List<AdminCouponVo> couponList
) {
    public static GetCouponsResponseDto of(List<AdminCouponVo> couponList) {
        return GetCouponsResponseDto.builder()
                .couponList(couponList)
                .build();
    }
}
