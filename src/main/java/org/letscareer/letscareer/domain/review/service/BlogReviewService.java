package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;

public interface BlogReviewService {
    void createBlogReview(CreateBlogReviewRequestDto requestDto);
}
