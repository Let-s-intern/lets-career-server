package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateBlogReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetBlogReviewResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogReviewService {
    GetBlogReviewResponseDto getBlogReviews(List<ProgramType> typeList, Pageable pageable);
    GetBlogReviewForAdminResponseDto getBlogReviewForAdmin();
    void createBlogReview(CreateBlogReviewRequestDto requestDto);
    void updateBlogReview(Long blogReviewId, UpdateBlogReviewRequestDto requestDto);
    void deleteBlogReview(Long blogReviewId);
}
