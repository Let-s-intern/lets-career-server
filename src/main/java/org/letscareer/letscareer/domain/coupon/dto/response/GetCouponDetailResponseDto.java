package org.letscareer.letscareer.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetCouponDetailResponseDto(
        AdminCouponDetailVo couponInfo
) {
    public static GetCouponDetailResponseDto of(AdminCouponDetailVo couponInfo) {
        return GetCouponDetailResponseDto.builder()
                .couponInfo(couponInfo)
                .build();
    }
}
