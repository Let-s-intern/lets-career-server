package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    GetReviewDetailResponseDto getReviewDetail(Long reviewId);

    void createReview(Long applicationId, CreateReviewRequestDto responseDto);

    void createReviewByLink(Long programId, ProgramType programType, CreateReviewRequestDto requestDto);

    void updateReview(Long reviewId, UpdateReviewRequestDto requestDto);

    void updateReviewVisibleStatus(Long reviewId, Boolean isVisible);

    GetReviewDetailListResponseDto getReviewAdminList(ProgramType programType, List<String> sortBy);
}
