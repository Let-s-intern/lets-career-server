package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.LiveApplication;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.LiveReview;
import org.letscareer.letscareer.domain.review.repository.LiveReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LiveReviewHelper {
    private final LiveReviewRepository liveReviewRepository;

    public LiveReview createLiveReviewAndSave(Live live, LiveApplication liveApplication, CreateReviewRequestDto requestDto) {
        LiveReview liveReview = LiveReview.createLiveReview(live, liveApplication, requestDto);
        return liveReviewRepository.save(liveReview);
    }
}
