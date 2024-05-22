package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.repository.ReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewHelper {
    private final ReviewRepository reviewRepository;

    public Review createReviewAndSave(Application application, CreateReviewRequestDto reviewRequestDto) {
        Review review = Review.createReview(application, reviewRequestDto);
        return reviewRepository.save(review);
    }
}
