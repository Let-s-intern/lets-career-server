package org.letscareer.letscareer.domain.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("payment")
@Getter
@Entity
public class Payment extends BaseTimeEntity {
    @Builder.Default
    private Integer finalPrice = 0;

    @Builder.Default
    private Boolean isConfirmed = false;

    @Builder.Default
    private Boolean isRefunded = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    private Price price;

    public static Payment createPayment(int finalPrice,
                                        Coupon coupon,
                                        Application application,
                                        Price price) {
        return Payment.builder()
                .finalPrice(finalPrice)
                .coupon(coupon)
                .application(application)
                .price(price)
                .build();
    }

    public void updatePayment(UpdatePaymentRequestDto updatePaymentRequestDto) {
        this.isConfirmed = updateValue(this.isConfirmed, updatePaymentRequestDto.isConfirmed());
        this.isRefunded = updateValue(this.isRefunded, updatePaymentRequestDto.isRefunded());
    }
}
