package org.letscareer.letscareer.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponProgramRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.coupon.mapper.CouponMapper;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponDetailVo;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;
import org.letscareer.letscareer.domain.couponprogram.helper.CouponProgramHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
        AdminCouponDetailVo couponDetailVo = couponHelper.getCouponDetail(couponId);
        return couponMapper.toGetCouponDetailResponseDto(couponDetailVo);
    }

    @Override
    public void createCoupon(CreateCouponRequestDto createCouponRequestDto) {
        Coupon newCoupon = couponHelper.createCouponAndSave(createCouponRequestDto);
        createCouponProgramListAndSave(createCouponRequestDto.programTypeList(), newCoupon);
    }

    @Override
    public void updateCoupon(Long couponId, CreateCouponRequestDto updateCouponRequestDto) {
        Coupon coupon = couponHelper.findCouponByIdOrThrow(couponId);
        coupon.updateCoupon(updateCouponRequestDto);
        updateCouponProgramList(updateCouponRequestDto.programTypeList(), coupon);
    }

    @Override
    public void deleteCoupon(Long couponId) {
        Coupon coupon = couponHelper.findCouponByIdOrThrow(couponId);
        couponHelper.deleteCoupon(coupon);
    }

    private void createCouponProgramListAndSave(List<CreateCouponProgramRequestDto> programTypeList, Coupon coupon) {
        programTypeList.stream()
                .map(requestDto -> couponProgramHelper.createCouponProgramAndSave(requestDto, coupon))
                .collect(Collectors.toList());
    }

    private void updateCouponProgramList(List<CreateCouponProgramRequestDto> couponProgramList, Coupon coupon) {
        if(Objects.isNull(couponProgramList)) return;
        couponProgramHelper.deleteCouponProgramsByCouponId(coupon.getId());
        coupon.setInitCouponProgramList();
        createCouponProgramListAndSave(couponProgramList, coupon);
    }
}
