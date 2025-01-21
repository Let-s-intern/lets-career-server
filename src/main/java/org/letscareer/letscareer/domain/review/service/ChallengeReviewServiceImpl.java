package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetMyReviewResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.entity.ChallengeReview;
import org.letscareer.letscareer.domain.review.helper.ChallengeReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.letscareer.letscareer.domain.review.mapper.ReviewMapper;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;
import org.letscareer.letscareer.domain.review.vo.*;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_ALREADY_EXISTS;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Service("CHALLENGE_REVIEW")
public class ChallengeReviewServiceImpl implements ReviewService {
    private final ChallengeReviewHelper challengeReviewHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final ReviewItemHelper reviewItemHelper;
    private final ReviewMapper reviewMapper;

    @Override
    public GetReviewForAdminResponseDto getReviewsForAdmin() {
        List<ReviewAdminVo> reviewAdminVos = challengeReviewHelper.findAllChallengeReviewAdminVos().stream()
                .map(challengeReviewAdminVo -> reviewMapper.toReviewAdminVo(
                        challengeReviewAdminVo,
                        reviewItemHelper.findAllReviewItemAdminVosByReviewId(challengeReviewAdminVo.reviewId())
                ))
                .collect(Collectors.toList());
        return reviewMapper.toGetReviewForAdminResponseDto(reviewAdminVos);
    }

    @Override
    public GetMyReviewResponseDto getReview(Long reviewId, User user) {
        ChallengeReviewVo challengeReviewVo = challengeReviewHelper.findChallengeReviewVoOrThrow(reviewId);
        validateAuthorizedUser(user, challengeReviewVo.userId());
        List<ReviewItemVo> reviewItemVos = reviewItemHelper.findAllReviewItemVosByReviewId(reviewId);
        ReviewMyVo reviewInfo = reviewMapper.toReviewMyVo(challengeReviewVo, reviewItemVos);
        return reviewMapper.toGetMyReviewResponseDto(reviewInfo);
    }

    @Override
    public void createReview(User user, Long applicationId, CreateReviewRequestDto requestDto) {
        ChallengeApplication challengeApplication = challengeApplicationHelper.findChallengeApplicationByIdOrThrow(applicationId);
        validateCreateReviewCondition(user, challengeApplication);
        Challenge challenge = challengeApplication.getChallenge();
        ChallengeReview challengeReview = challengeReviewHelper.createChallengeReviewAndSave(challenge, challengeApplication, requestDto);
        createReviewItemListAndSave(challengeReview, challengeApplication.getGoal(), requestDto.reviewItemList());
    }

    @Override
    public void updateReview(Long reviewId, UpdateReviewRequestDto requestDto) {
        ChallengeReview challengeReview = challengeReviewHelper.findChallengeReviewByReviewIdOrThrow(reviewId);
        challengeReview.updateReview(requestDto);
    }

    private void validateCreateReviewCondition(User currentUser, ChallengeApplication challengeApplication) {
        validateAuthorizedUser(currentUser, challengeApplication.getUser().getId());
        validateReviewAlreadyExists(challengeApplication);
    }

    private void validateAuthorizedUser(User currentUser, Long applicationUserId) {
        if(!Objects.equals(currentUser.getId(), applicationUserId)) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }
    }

    private void validateReviewAlreadyExists(ChallengeApplication challengeApplication) {
        if(!Objects.isNull(challengeApplication.getReview())) {
            throw new ConflictException(REVIEW_ALREADY_EXISTS);
        }
    }

    private void createReviewItemListAndSave(ChallengeReview challengeReview, String goal, List<CreateReviewItemVo> createReviewItemVoList) {
        CreateReviewItemVo goalReviewItemVo = new CreateReviewItemVo(ReviewQuestionType.GOAL, goal);
        reviewItemHelper.createReviewItemAndSave(challengeReview, goalReviewItemVo);
        createReviewItemVoList.forEach(createReviewItemVo -> {
            reviewItemHelper.createReviewItemAndSave(challengeReview, createReviewItemVo);
        });
    }
}
