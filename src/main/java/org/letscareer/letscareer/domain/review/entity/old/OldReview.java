package org.letscareer.letscareer.domain.review.entity.old;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.type.converter.ProgramTypeConverter;
import org.letscareer.letscareer.domain.review.dto.request.CreateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateOldReviewRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class OldReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "old_review_id")
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
    private String programDetail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public static OldReview createReview(Application application, CreateOldReviewRequestDto reviewRequestDto) {
        OldReview review = OldReview.builder()
                .nps(reviewRequestDto.nps())
                .npsAns(reviewRequestDto.npsAns())
                .npsCheckAns(reviewRequestDto.npsCheckAns())
                .content(reviewRequestDto.content())
                .score(reviewRequestDto.score())
                .programDetail(reviewRequestDto.programDetail())
                .application(application)
                .build();
        application.setOldReview(review);
        return review;
    }

    public static OldReview createReviewByLink(Long programId, ProgramType programType, CreateOldReviewRequestDto reviewRequestDto) {
        OldReview review = OldReview.builder()
                .nps(reviewRequestDto.nps())
                .npsAns(reviewRequestDto.npsAns())
                .npsCheckAns(reviewRequestDto.npsCheckAns())
                .content(reviewRequestDto.content())
                .score(reviewRequestDto.score())
                .programDetail(reviewRequestDto.programDetail())
                .programId(programId)
                .programType(programType)
                .build();
        return review;
    }

    public void updateReview(UpdateOldReviewRequestDto requestDto) {
        this.nps = updateValue(this.nps, requestDto.nps());
        this.npsAns = updateValue(this.npsAns, requestDto.npsAns());
        this.npsCheckAns = updateValue(this.npsCheckAns, requestDto.npsCheckAns());
        this.content = updateValue(this.content, requestDto.content());
        this.score = updateValue(this.score, requestDto.score());
        this.programDetail = updateValue(this.programDetail, requestDto.programDetail());
    }

    public void updateIsVisibleStatus(Boolean isVisible) {
        this.isVisible = updateValue(this.isVisible, isVisible);
    }
}
