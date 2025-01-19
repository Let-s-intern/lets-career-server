package org.letscareer.letscareer.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionTypeConverter;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;
import org.letscareer.letscareer.global.common.entity.BaseTimeEntity;

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

    @Builder.Default
    private Boolean isVisible = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public static ReviewItem createReviewItem(Review review, CreateReviewItemVo createReviewItemVo) {
        ReviewItem reviewItem = ReviewItem.builder()
                .review(review)
                .questionType(createReviewItemVo.questionType())
                .answer(createReviewItemVo.answer())
                .build();
        review.addReviewItem(reviewItem);
        return reviewItem;
    }
}
