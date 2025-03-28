package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.dto.response.GetLiveMentorReviewResponseDto;
import org.letscareer.letscareer.domain.review.entity.LiveReview;
import org.letscareer.letscareer.domain.review.repository.LiveReviewRepository;
import org.letscareer.letscareer.domain.review.vo.LiveReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.LiveReviewVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class LiveReviewHelper {
    private final LiveReviewRepository liveReviewRepository;

    public LiveReview createLiveReviewAndSave(Live live, LiveApplication liveApplication, CreateReviewRequestDto requestDto) {
        LiveReview liveReview = LiveReview.createLiveReview(live, liveApplication, requestDto);
        return liveReviewRepository.save(liveReview);
    }

    public LiveReview findLiveReviewByReviewIdOrThrow(Long reviewId) {
        return liveReviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public List<LiveReviewAdminVo> findAllLiveReviewAdminVos() {
        return liveReviewRepository.findAllLiveReviewAdminVos();
    }

    public LiveReviewVo findLiveReviewVoOrThrow(Long reviewId) {
        return liveReviewRepository.findLiveReviewVoByReviewId(reviewId).orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public List<GetLiveMentorReviewResponseDto> findLiveReviewContentByLiveId(Long liveId) {
        return liveReviewRepository.findLiveReviewByLiveId(liveId);
    }
}
