package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.EntityUpdateValueUtils.updateValue;

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
    private Boolean npsCheckAns;
    private String content;
    private Integer score;
    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    private Long programId;
    @Builder.Default
    private Boolean isVisible = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public static Review createReview(Application application, CreateReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .nps(reviewRequestDto.nps())
                .npsAns(reviewRequestDto.npsAns())
                .npsCheckAns(reviewRequestDto.npsCheckAns())
                .content(reviewRequestDto.content())
                .score(reviewRequestDto.score())
                .application(application)
                .build();
        application.setReview(review);
        return review;
    }

    public static Review createReviewByLink(Long programId, ProgramType programType, CreateReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .nps(reviewRequestDto.nps())
                .npsAns(reviewRequestDto.npsAns())
                .npsCheckAns(reviewRequestDto.npsCheckAns())
                .content(reviewRequestDto.content())
                .score(reviewRequestDto.score())
                .programId(programId)
                .programType(programType)
                .build();
        return review;
    }

    public void updateReview(UpdateReviewRequestDto requestDto) {
        this.nps = updateValue(this.nps, requestDto.nps());
        this.npsAns = updateValue(this.npsAns, requestDto.npsAns());
        this.npsCheckAns = updateValue(this.npsCheckAns, requestDto.npsCheckAns());
        this.content = updateValue(this.content, requestDto.content());
        this.score = updateValue(this.score, requestDto.score());
    }

    public void updateIsVisibleStatus(Boolean isVisible) {
        this.isVisible = updateValue(this.isVisible, isVisible);
    }
}
