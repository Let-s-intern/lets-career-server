package org.letscareer.letscareer.domain.review.service;

import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OldReviewService {
    GetOldReviewDetailResponseDto getReviewDetail(Long reviewId);

    void createReview(Long applicationId, CreateOldReviewRequestDto responseDto);

    void createReviewByLink(Long programId, ProgramType programType, CreateOldReviewRequestDto requestDto);

    void updateReview(Long reviewId, UpdateOldReviewRequestDto requestDto);

    void updateReviewVisibleStatus(Long reviewId, Boolean isVisible);

    GetOldReviewDetailListResponseDto getReviewDetailList(User user, ProgramType programType, List<String> sortBy);
}
