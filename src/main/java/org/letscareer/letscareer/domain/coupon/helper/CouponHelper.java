package org.letscareer.letscareer.domain.coupon.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.coupon.repository.CouponRepository;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.coupon.vo.AdminCouponVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.coupon.error.CouponErrorCode.COUPON_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class CouponHelper {
    private final CouponRepository couponRepository;

    public Coupon findCouponByIdOrThrow(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new EntityNotFoundException(COUPON_NOT_FOUND));
    }

    public Coupon findCouponByIdOrNull(Long couponId) {
        return couponId != null ? couponRepository.findById(couponId).orElse(null) : null;
    }

    public Coupon createCouponAndSave(CreateCouponRequestDto createCouponRequestDto) {
        Coupon newCoupon = Coupon.createCoupon(createCouponRequestDto);
        return couponRepository.save(newCoupon);
    }

    public List<AdminCouponVo> getCoupons() {
        return couponRepository.findAllAdminCouponVos();
    }
}
