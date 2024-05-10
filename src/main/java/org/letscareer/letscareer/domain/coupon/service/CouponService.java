package org.letscareer.letscareer.domain.coupon.service;

import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface CouponService {
    GetCouponsResponseDto getCoupons();
    GetCouponDetailResponseDto getCouponDetail(Long couponId);
    void createCoupon(CreateCouponRequestDto couponRequestDto);

    void updateCoupon(Long couponId, CreateCouponRequestDto couponRequestDto);

    void deleteCoupon(Long couponId);
}
