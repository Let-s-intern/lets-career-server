package org.letscareer.letscareer.domain.coupon.mapper;

import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponDetailVo;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CouponMapper {
    public Coupon toEntity(CreateCouponRequestDto couponRequestDto) {
        return Coupon.createCoupon(couponRequestDto);
    }

    public GetCouponsResponseDto toGetCouponsResponseDto(List<AdminCouponVo> couponList) {
        return GetCouponsResponseDto.of(couponList);
    }

    public GetCouponDetailResponseDto toGetCouponDetailResponseDto(AdminCouponDetailVo couponDetailVo) {
        return GetCouponDetailResponseDto.of(couponDetailVo);
    }
}
