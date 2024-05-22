package org.letscareer.letscareer.domain.couponprogram.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponProgramRequestDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
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
}
