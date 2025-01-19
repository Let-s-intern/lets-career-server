package org.letscareer.letscareer.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.request.UpdateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetReviewForAdminResponseDto;
import org.letscareer.letscareer.domain.review.entity.LiveReview;
import org.letscareer.letscareer.domain.review.helper.LiveReviewHelper;
import org.letscareer.letscareer.domain.review.helper.ReviewItemHelper;
import org.letscareer.letscareer.domain.review.vo.CreateReviewItemVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_ALREADY_EXISTS;
import static org.letscareer.letscareer.global.error.GlobalErrorCode.UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Service("LIVE_REVIEW")
public class LiveReviewServiceImpl implements ReviewService {
    private final LiveReviewHelper liveReviewHelper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final ReviewItemHelper reviewItemHelper;

    @Override
    public GetReviewForAdminResponseDto getReviewForAdmin() {
        return null;
    }

    @Override
    public void createReview(User user, Long applicationId, CreateReviewRequestDto requestDto) {
        LiveApplication liveApplication = liveApplicationHelper.findLiveApplicationByIdOrThrow(applicationId);
        validateCreateReviewCondition(user, liveApplication);
        Live live = liveApplication.getLive();
        LiveReview liveReview = liveReviewHelper.createLiveReviewAndSave(live, liveApplication, requestDto);
        createReviewItemListAndSave(liveReview, requestDto.reviewItemList());
    }

    @Override
    public void updateReview(Long reviewId, UpdateReviewRequestDto requestDto) {
        LiveReview liveReview = liveReviewHelper.findLiveReviewByReviewIdOrThrow(reviewId);
        liveReview.updateReview(requestDto);
    }

    private void validateCreateReviewCondition(User currentUser, LiveApplication liveApplication) {
        if(!Objects.equals(currentUser.getId(), liveApplication.getUser().getId())) {
            throw new UnauthorizedException(UNAUTHORIZED);
        }

        if(!Objects.isNull(liveApplication.getReview())) {
            throw new ConflictException(REVIEW_ALREADY_EXISTS);
        }
    }

    private void createReviewItemListAndSave(LiveReview liveReview, List<CreateReviewItemVo> reviewItemList) {
        reviewItemList.forEach(createReviewItemVo -> {
            reviewItemHelper.createReviewItemAndSave(liveReview, createReviewItemVo);
        });
    }
}
