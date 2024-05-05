package org.letscareer.letscareer.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.coupon.type.CouponProgramType;
import org.letscareer.letscareer.domain.coupon.type.converter.CouponProgramTypeConverter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class CouponProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_program_id")
    private Long id;
    @Convert(converter = CouponProgramTypeConverter.class)
    private CouponProgramType couponProgramType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
}
