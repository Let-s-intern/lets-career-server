package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.helper.ReviewHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewHelper reviewHelper;
    private final ReviewMapper reviewMapper;
    private final ApplicationHelper applicationHelper;
    private final ChallengeHelper challengeHelper;
    private final LiveHelper liveHelper;

    @Override
    public GetReviewDetailResponseDto getReviewDetail(Long reviewId) {
        ReviewDetailVo reviewVo = reviewHelper.findReviewDetailVoOrThrow(reviewId);
        return reviewMapper.toGetReviewDetailResponseDto(reviewVo);
    }

    @Override
    public void createReview(Long applicationId, CreateReviewRequestDto requestDto) {
        Application application = applicationHelper.findByIdOrThrow(applicationId);
        reviewHelper.createReviewAndSave(application, requestDto);
    }

    @Override
    public void createReviewByLink(Long programId, ProgramType programType, CreateReviewRequestDto requestDto) {
        validateProgram(programId, programType);
        reviewHelper.createReviewByLinkAndSave(programId, programType, requestDto);
    }

    @Override
    public void updateReview(Long reviewId, UpdateReviewRequestDto requestDto) {
        Review review = reviewHelper.findReviewOrThrow(reviewId);
        review.updateReview(requestDto);
    }

    @Override
    public void updateReviewVisibleStatus(Long reviewId, Boolean isVisible) {
        Review review = reviewHelper.findReviewOrThrow(reviewId);
        review.updateIsVisibleStatus(isVisible);
    }

    @Override
    public GetReviewDetailListResponseDto getReviewAdminList(ProgramType programType, List<String> sortBy) {
        List<ReviewAdminVo> reviewAdminVoList = reviewHelper.findReviewAdminVos(programType, sortBy);
        return reviewMapper.toGetReviewDetailListResponseDto(reviewAdminVoList);
    }

    private void validateProgram(Long programId, ProgramType programType) {
        switch (programType) {
            case CHALLENGE -> {
                challengeHelper.findChallengeByIdOrThrow(programId);
            }
            case LIVE -> {
                liveHelper.findLiveByIdOrThrow(programId);
            }
        }
    }
}
