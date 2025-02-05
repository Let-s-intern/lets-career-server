package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.entity.ReviewItem;
import org.letscareer.letscareer.domain.review.repository.ReviewItemRepository;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;
import org.letscareer.letscareer.domain.review.vo.ReviewItemAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewItemVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ReviewItemHelper {
    private final ReviewItemRepository reviewItemRepository;

    public void createReviewItemAndSave(Review review, CreateReviewItemVo createReviewItemVo) {
        ReviewItem reviewItem = ReviewItem.createReviewItem(review, createReviewItemVo);
        reviewItemRepository.save(reviewItem);
    }

    public ReviewItem findReviewItemByReviewItemIdOrThrow(Long reviewItemId) {
        return reviewItemRepository.findById(reviewItemId).orElseThrow(() -> new EntityNotFoundException(REVIEW_ITEM_NOT_FOUND));
    }

    public List<ReviewItemVo> findAllReviewItemVosByReviewId(Long reviewId, Boolean isVisible) {
        return reviewItemRepository.findAllReviewItemVosByReviewId(reviewId, isVisible);
    }

    public List<ReviewItemAdminVo> findAllReviewItemAdminVosByReviewId(Long reviewId) {
        return reviewItemRepository.findAllReviewItemAdminVosByReviewId(reviewId);
    }
}
