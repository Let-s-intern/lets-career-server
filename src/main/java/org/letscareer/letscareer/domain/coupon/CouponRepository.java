package org.letscareer.letscareer.domain.coupon;

import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
