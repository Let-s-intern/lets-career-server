package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetMyReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    GetReviewForAdminResponseDto getReviewsForAdmin();
    GetMyReviewResponseDto getReview(Long reviewId, User user);
    void createReview(User user, Long applicationId, CreateReviewRequestDto requestDto);
    void updateReview(Long reviewId, UpdateReviewRequestDto requestDto);
}
