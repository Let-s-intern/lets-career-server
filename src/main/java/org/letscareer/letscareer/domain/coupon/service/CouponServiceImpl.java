package org.letscareer.letscareer.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponProgramRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.coupon.mapper.CouponMapper;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;
import org.letscareer.letscareer.domain.couponprogram.helper.CouponProgramHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponHelper couponHelper;
    private final CouponMapper couponMapper;
    private final CouponProgramHelper couponProgramHelper;

    @Override
    public GetCouponsResponseDto getCoupons() {
        List<AdminCouponVo> couponList = couponHelper.getCoupons();
        return couponMapper.toGetCouponsResponseDto(couponList);
    }

    @Override
    public GetCouponDetailResponseDto getCouponDetail(Long couponId) {
        return null;
    }

    @Override
    public void createCoupon(CreateCouponRequestDto createCouponRequestDto) {
        Coupon newCoupon = couponHelper.createCouponAndSave(createCouponRequestDto);
        couponProgramListAndSave(createCouponRequestDto.programTypeList(), newCoupon);
    }

    @Override
    public void updateCoupon(Long couponId, CreateCouponRequestDto couponRequestDto) {

    }

    @Override
    public void deleteCoupon(Long couponId) {

    }

    private void couponProgramListAndSave(List<CreateCouponProgramRequestDto> programTypeList, Coupon coupon) {
        programTypeList.stream()
                .map(requestDto -> couponProgramHelper.createCouponProgramAndSave(requestDto, coupon))
                .collect(Collectors.toList());
    }
}
