package org.letscareer.letscareer.domain.couponprogram.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponProgramRequestDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
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

    public static CouponProgram createCouponProgram(CreateCouponProgramRequestDto requestDto, Coupon coupon) {
        return CouponProgram.builder()
                .couponProgramType(requestDto.programType())
                .coupon(coupon)
                .build();
    }
}
