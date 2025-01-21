package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;

public interface BlogReviewService {
    GetBlogReviewForAdminResponseDto getBlogReviewForAdmin();
    void createBlogReview(CreateBlogReviewRequestDto requestDto);
    void updateBlogReview(Long blogReviewId, UpdateBlogReviewRequestDto requestDto);
    void deleteBlogReview(Long blogReviewId);
}
