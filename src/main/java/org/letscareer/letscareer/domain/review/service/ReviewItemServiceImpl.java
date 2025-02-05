package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewItemRequestDto;
import org.letscareer.letscareer.domain.review.entity.ReviewItem;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewItemServiceImpl implements ReviewItemService {
    private final ReviewItemHelper reviewItemHelper;

    @Override
    public void updateReviewItem(Long reviewItemId, UpdateReviewItemRequestDto requestDto) {
        ReviewItem reviewItem = reviewItemHelper.findReviewItemByReviewItemIdOrThrow(reviewItemId);
        reviewItem.updateReviewItem(requestDto);
    }
}
