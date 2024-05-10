package org.letscareer.letscareer.domain.coupon.dto.request;

import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;

public record CreateCouponProgramRequestDto(
        CouponProgramType programType
) {
}
