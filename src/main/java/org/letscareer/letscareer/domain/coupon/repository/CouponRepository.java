package org.letscareer.letscareer.domain.coupon.repository;

import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponQueryRepository {
}
