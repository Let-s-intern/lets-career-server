package org.letscareer.letscareer.domain.couponprogram.repository;

import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.couponprogram.entity.CouponProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponProgramRepository extends JpaRepository<CouponProgram, Long> {
    Optional<CouponProgram> findByCouponProgramTypeAndCouponId(CouponProgramType programType, Long couponId);

    void deleteAllByCouponId(Long couponId);

    boolean existsByCouponIdAndCouponProgramType(Long couponId, CouponProgramType couponProgramType);
}
