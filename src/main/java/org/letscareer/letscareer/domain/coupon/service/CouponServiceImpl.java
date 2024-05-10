package org.letscareer.letscareer.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponServiceImpl implements CouponService{
    @Override
    public GetCouponsResponseDto getCoupons() {
        return null;
    }

    @Override
    public GetCouponDetailResponseDto getCouponDetail(Long couponId) {
        return null;
    }

    @Override
    public void createCoupon(CreateCouponRequestDto couponRequestDto) {

    }

    @Override
    public void updateCoupon(Long couponId, CreateCouponRequestDto couponRequestDto) {

    }

    @Override
    public void deleteCoupon(Long couponId) {

    }
}
