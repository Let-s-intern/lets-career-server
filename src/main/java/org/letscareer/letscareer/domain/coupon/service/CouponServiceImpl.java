package org.letscareer.letscareer.domain.coupon.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponProgramRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.dto.response.CouponApplyResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponDetailResponseDto;
import org.letscareer.letscareer.domain.coupon.dto.response.GetCouponsResponseDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.helper.CouponHelper;
import org.letscareer.letscareer.domain.coupon.mapper.CouponMapper;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponDetailVo;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;
import org.letscareer.letscareer.domain.couponprogram.helper.CouponProgramHelper;
import org.letscareer.letscareer.domain.payment.helper.PaymentHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.coupon.error.CouponErrorCode.COUPON_NOT_AVAILABLE_PROGRAM_TYPE;
import static org.letscareer.letscareer.domain.coupon.error.CouponErrorCode.COUPON_NOT_AVAILABLE_TIME;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponServiceImpl implements CouponService {
    private final CouponHelper couponHelper;
    private final CouponMapper couponMapper;
    private final CouponProgramHelper couponProgramHelper;
    private final PaymentHelper paymentHelper;

    @Override
    public CouponApplyResponseDto applyCoupon(User user, String code, CouponProgramType programType) {
        Coupon coupon = couponHelper.findCouponByCodeOrThrow(code);
        couponHelper.validateDateOfCoupon(coupon.getStartDate(), coupon.getEndDate());
        validateProgramTypeOfCoupon(coupon.getId(), programType);
        validateRemainTimeOfCoupon(user.getId(), coupon.getId(), coupon.getTime());
        return couponMapper.toCouponApplyResponseDto(coupon.getId(), coupon.getDiscount());
    }

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

    private void validateProgramTypeOfCoupon(Long couponId, CouponProgramType programType) {
        if(!couponProgramHelper.existsCouponProgramType(couponId, programType)) {
            throw new InvalidValueException(COUPON_NOT_AVAILABLE_PROGRAM_TYPE);
        }
    }

    private void validateRemainTimeOfCoupon(Long userId, Long couponId, Integer couponTime) {
        if(couponTime < 0) return;
        long couponAppliedTime = paymentHelper.countCouponAppliedTime(userId, couponId);
        if(couponAppliedTime >= couponTime) {
            throw new InvalidValueException(COUPON_NOT_AVAILABLE_TIME);
        }
    }
}
