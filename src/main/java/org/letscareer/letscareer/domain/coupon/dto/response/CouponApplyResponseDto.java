package org.letscareer.letscareer.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CouponApplyResponseDto(
        Long couponId,
        Integer discount
) {
    public static CouponApplyResponseDto of(Long couponId, Integer discount) {
        return CouponApplyResponseDto.builder()
                .couponId(couponId)
                .discount(discount)
                .build();
    }
}
