package org.letscareer.letscareer.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.coupon.dto.request.CreateCouponRequestDto;
import org.letscareer.letscareer.domain.coupon.type.CouponType;
import org.letscareer.letscareer.domain.coupon.type.converter.CouponTypeConverter;
import org.letscareer.letscareer.domain.couponprogram.entity.CouponProgram;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

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
    private List<Payment> paymentList = new ArrayList<>();
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    @Builder.Default
    private List<CouponProgram> couponProgramList = new ArrayList<>();

    public static Coupon createCoupon(CreateCouponRequestDto couponRequestDto) {
        return Coupon.builder()
                .name(couponRequestDto.name())
                .code(couponRequestDto.code())
                .discount(couponRequestDto.discount())
                .time(couponRequestDto.time())
                .couponType(couponRequestDto.couponType())
                .startDate(couponRequestDto.startDate())
                .endDate(couponRequestDto.endDate())
                .build();
    }

    public void addCouponProgram(CouponProgram couponProgram) {
        this.couponProgramList.add(couponProgram);
    }

    public void setInitCouponProgramList() {
        this.couponProgramList = new ArrayList<>();
    }

    public void updateCoupon(CreateCouponRequestDto couponRequestDto) {
        this.couponType = updateValue(this.couponType, couponRequestDto.couponType());
        this.name = updateValue(this.name, couponRequestDto.name());
        this.code = updateValue(this.code, couponRequestDto.code());
        this.discount = updateValue(this.discount, couponRequestDto.discount());
        this.time = updateValue(this.time, couponRequestDto.time());
        this.startDate = updateValue(this.startDate, couponRequestDto.startDate());
        this.endDate = updateValue(this.endDate, couponRequestDto.endDate());
    }
}
