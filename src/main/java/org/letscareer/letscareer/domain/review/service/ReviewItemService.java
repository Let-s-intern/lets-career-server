package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewItemRequestDto;

public interface ReviewItemService {
    void updateReviewItem(Long reviewItemId, UpdateReviewItemRequestDto requestDto);
}
