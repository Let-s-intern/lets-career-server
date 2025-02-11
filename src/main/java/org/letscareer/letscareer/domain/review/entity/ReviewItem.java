package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewItemRequestDto;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionTypeConverter;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

import static org.letscareer.letscareer.global.common.utils.entity.EntityUpdateValueUtils.updateValue;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review_item")
@Entity
public class ReviewItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_item_id")
    private Long id;

    @Convert(converter = ReviewQuestionTypeConverter.class)
    private ReviewQuestionType questionType;

    private String answer;

    @NotNull
    private Boolean isVisible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public static ReviewItem createReviewItem(Review review, CreateReviewItemVo createReviewItemVo) {
        ReviewItem reviewItem = ReviewItem.builder()
                .review(review)
                .questionType(createReviewItemVo.questionType())
                .answer(createReviewItemVo.answer())
                .isVisible(isVisibleByQuestionType(createReviewItemVo.questionType()))
                .build();
        review.addReviewItem(reviewItem);
        return reviewItem;
    }

    public void updateReviewItem(UpdateReviewItemRequestDto requestDto) {
        this.isVisible = updateValue(this.isVisible, requestDto.isVisible());
    }

    private static boolean isVisibleByQuestionType(ReviewQuestionType questionType) {
        return questionType.equals(ReviewQuestionType.GOAL) || questionType.equals(ReviewQuestionType.GOAL_RESULT)
                || questionType.equals(ReviewQuestionType.WORRY) || questionType.equals(ReviewQuestionType.WORRY_RESULT);
    }
}
