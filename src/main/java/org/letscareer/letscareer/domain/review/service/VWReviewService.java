package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.dto.response.GetReviewResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface VWReviewService {
    GetReviewResponseDto getReviews();
}
