package org.letscareer.letscareer.domain.couponprogram.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponProgramRequestDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.couponprogram.entity.CouponProgram;
import org.letscareer.letscareer.domain.couponprogram.repository.CouponProgramRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class CouponProgramHelper {
    private final CouponProgramRepository couponProgramRepository;

    public CouponProgram createCouponProgramAndSave(CreateCouponProgramRequestDto requestDto, Coupon coupon) {
        CouponProgram newCouponProgram = CouponProgram.createCouponProgram(requestDto, coupon);
        return couponProgramRepository.save(newCouponProgram);
    }

    public CouponProgram findByProgramTypeOrSave(CreateCouponProgramRequestDto requestDto, Coupon coupon) {
        CouponProgram couponProgram = couponProgramRepository.findByCouponProgramTypeAndCouponId(requestDto.programType(), coupon.getId()).orElse(null);
        if(couponProgram == null) couponProgram = CouponProgram.createCouponProgram(requestDto, coupon);
        return couponProgram;
    }

    public void deleteCouponProgramsByCouponId(Long couponId) {
        couponProgramRepository.deleteAllByCouponId(couponId);
    }

    public boolean existsCouponProgramType(Long couponId, CouponProgramType programType) {
        if(programType.equals(CouponProgramType.REPORT))
            return couponProgramRepository.existsByCouponIdAndCouponProgramType(couponId, programType);
        return couponProgramRepository.existsByCouponIdAndCouponProgramType(couponId, CouponProgramType.ALL)
                || couponProgramRepository.existsByCouponIdAndCouponProgramType(couponId, programType);
    }
}
