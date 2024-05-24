package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.repository.ReviewRepository;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewHelper {
    private final ReviewRepository reviewRepository;

    public Review createReviewAndSave(Application application, CreateReviewRequestDto reviewRequestDto) {
        Review review = Review.createReview(application, reviewRequestDto);
        return reviewRepository.save(review);
    }

    public Page<ReviewVo> findChallengeReviewVos(Long challengeId, Pageable pageable) {
        return reviewRepository.findChallengeReviewVos(challengeId, pageable);
    }

    public Page<ReviewVo> findLiveReviewVos(Long liveId, Pageable pageable) {
        return reviewRepository.findLiveReviewVos(liveId, pageable);
    }
}
