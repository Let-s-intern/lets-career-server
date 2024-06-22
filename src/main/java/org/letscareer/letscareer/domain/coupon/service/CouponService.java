package org.letscareer.letscareer.domain.coupon.service;

import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.CouponApplyResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CouponService {
    CouponApplyResponseDto applyCoupon(User user, String code, CouponProgramType programType);

    GetCouponsResponseDto getCoupons();

    GetCouponDetailResponseDto getCouponDetail(Long couponId);

    void createCoupon(CreateCouponRequestDto couponRequestDto);

    void updateCoupon(Long couponId, CreateCouponRequestDto couponRequestDto);

    void deleteCoupon(Long couponId);
}
