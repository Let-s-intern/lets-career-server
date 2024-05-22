package org.letscareer.letscareer.domain.application.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Entity
public abstract class Application extends BaseTimeEntity {
    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public Application(User user) {
        this.user = user;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
