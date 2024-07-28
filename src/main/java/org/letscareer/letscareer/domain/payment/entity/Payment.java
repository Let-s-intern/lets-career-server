package org.letscareer.letscareer.domain.payment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.challenge.dto.request.UpdateChallengeApplicationPaybackRequestDto;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.dto.request.CreatePaymentRequestDto;
import org.letscareer.letscareer.domain.payment.dto.request.UpdatePaymentRequestDto;
import org.letscareer.letscareer.domain.price.entity.Price;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@DiscriminatorValue("payment")
@Getter
@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;
    @Builder.Default
    private Integer finalPrice = 0;
    @Builder.Default
    private Integer programPrice = 0;
    @Builder.Default
    private Integer programDiscount = 0;
    @Builder.Default
    private Boolean isRefunded = false;
    @NotNull
    private String paymentKey;
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public static Payment createPayment(CreatePaymentRequestDto paymentInfo,
                                        Coupon coupon,
                                        Application application,
                                        Price price) {
        return Payment.builder()
                .finalPrice(Integer.valueOf(paymentInfo.amount()))
                .programPrice(price.getPrice())
                .programDiscount(price.getDiscount())
                .paymentKey(updateValue("", paymentInfo.paymentKey()))
                .orderId(paymentInfo.orderId())
                .coupon(coupon)
                .application(application)
                .build();
    }

    public void updatePayment(UpdatePaymentRequestDto requestDto) {
        this.isRefunded = updateValue(this.isRefunded, requestDto.isRefunded());
    }

    public void updateRefund(UpdateChallengeApplicationPaybackRequestDto requestDto) {
        this.isRefunded = updateValue(this.isRefunded, requestDto.isRefunded());
    }

    public void updateRefundPrice(Integer price) {
        this.finalPrice = price;
    }
}
