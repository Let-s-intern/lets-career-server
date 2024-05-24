package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    void createReview(Long applicationId, CreateReviewRequestDto responseDto);
}
