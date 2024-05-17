package org.letscareer.letscareer.domain.coupon.mapper;

import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.springframework.stereotype.Component;

@Component
public class CouponMapper {
    public Coupon toEntity(CreateCouponRequestDto couponRequestDto) {
        return Coupon.createCoupon(couponRequestDto);
    }
}
