package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    private Integer nps;
    private String npsAns;
    private String content;
    private Integer score;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public static Review createReview(Application application, CreateReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .nps(reviewRequestDto.nps())
                .content(reviewRequestDto.content())
                .score(reviewRequestDto.score())
                .application(application)
                .build();
        application.setReview(review);
        return review;
    }
}
