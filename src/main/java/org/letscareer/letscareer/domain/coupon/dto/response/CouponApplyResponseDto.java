package org.letscareer.letscareer.domain.coupon.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CouponApplyResponseDto(
        Integer discount
) {
    public static CouponApplyResponseDto of(Integer discount) {
        return CouponApplyResponseDto.builder()
                .discount(discount)
                .build();
    }
}
