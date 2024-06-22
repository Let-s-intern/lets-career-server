package org.letscareer.letscareer.domain.coupon.repository;

import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponQueryRepository {
    Optional<Coupon> findByCode(String code);
}
