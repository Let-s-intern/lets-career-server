package org.letscareer.letscareer.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.coupon.type.CouponType;
import org.letscareer.letscareer.domain.coupon.type.converter.CouponTypeConverter;
import org.letscareer.letscareer.domain.price.entity.UserPayment;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;
    private String name;
    private String code;
    private Integer discount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer time;
    @Convert(converter = CouponTypeConverter.class)
    private CouponType couponType;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @Builder.Default
    private List<UserPayment> userPaymentList = new ArrayList<>();
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CouponProgram> couponProgramList = new ArrayList<>();
}
