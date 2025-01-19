package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.entity.ReviewItem;
import org.letscareer.letscareer.domain.review.repository.ReviewItemRepository;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewItemHelper {
    private final ReviewItemRepository reviewItemRepository;

    public void createReviewItemAndSave(Review review, CreateReviewItemVo createReviewItemVo) {
        ReviewItem reviewItem = ReviewItem.createReviewItem(review, createReviewItemVo);
        reviewItemRepository.save(reviewItem);
    }
}
