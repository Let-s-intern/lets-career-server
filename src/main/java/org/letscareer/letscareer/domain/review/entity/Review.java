package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Entity
public abstract class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
    private Integer score;
    private Integer npsScore;
    private Boolean isVisible;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewItem> reviewItemList;

    public Review(Application application, CreateReviewRequestDto requestDto) {
        this.score = requestDto.score();
        this.npsScore = requestDto.npsScore();
        this.isVisible = true;
        this.application = application;
        this.reviewItemList = new ArrayList<>();
    }

    public void addReviewItem(ReviewItem reviewItem) {
        this.reviewItemList.add(reviewItem);
    }

    public void updateReview(UpdateReviewRequestDto requestDto) {
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }
}
