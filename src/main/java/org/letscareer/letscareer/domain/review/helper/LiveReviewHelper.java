package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.LiveReview;
import org.letscareer.letscareer.domain.review.repository.LiveReviewRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

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
}
