package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.application.helper.ApplicationHelper;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateOldReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailListResponseDto;
import org.letscareer.letscareer.domain.review.dto.response.GetOldReviewDetailResponseDto;
import org.letscareer.letscareer.domain.review.entity.old.OldReview;
import org.letscareer.letscareer.domain.review.helper.OldReviewHelper;
import org.letscareer.letscareer.domain.review.mapper.OldReviewMapper;
import org.letscareer.letscareer.domain.review.vo.old.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.ReviewDetailVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class OldOldReviewServiceImpl implements OldReviewService {
    private final OldReviewHelper oldReviewHelper;
    private final OldReviewMapper oldReviewMapper;
    private final ApplicationHelper applicationHelper;
    private final ChallengeHelper challengeHelper;
    private final LiveHelper liveHelper;

    @Override
    public GetOldReviewDetailResponseDto getReviewDetail(Long reviewId) {
        ReviewDetailVo reviewVo = oldReviewHelper.findReviewDetailVoOrThrow(reviewId);
        return oldReviewMapper.toGetReviewDetailResponseDto(reviewVo);
    }

    @Override
    public void createReview(Long applicationId, CreateOldReviewRequestDto requestDto) {
        Application application = applicationHelper.findByIdOrThrow(applicationId);
        oldReviewHelper.createReviewAndSave(application, requestDto);
    }

    @Override
    public void createReviewByLink(Long programId, ProgramType programType, CreateOldReviewRequestDto requestDto) {
        validateProgram(programId, programType);
        oldReviewHelper.createReviewByLinkAndSave(programId, programType, requestDto);
    }

    @Override
    public void updateReview(Long reviewId, UpdateOldReviewRequestDto requestDto) {
        OldReview review = oldReviewHelper.findReviewOrThrow(reviewId);
        review.updateReview(requestDto);
    }

    @Override
    public void updateReviewVisibleStatus(Long reviewId, Boolean isVisible) {
        OldReview review = oldReviewHelper.findReviewOrThrow(reviewId);
        review.updateIsVisibleStatus(isVisible);
    }

    @Override
    public GetOldReviewDetailListResponseDto getReviewDetailList(User user, ProgramType programType, List<String> sortBy) {
        Boolean isVisible = true;
        if(!Objects.isNull(user) && user.getRole().equals(UserRole.ADMIN)) isVisible = null;
        List<ReviewAdminVo> reviewAdminVoList = oldReviewHelper.findReviewAdminVos(isVisible, programType, sortBy);
        return oldReviewMapper.toGetReviewDetailListResponseDto(reviewAdminVoList);
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
