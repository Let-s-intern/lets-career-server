package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.Application;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.repository.ReviewRepository;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ReviewHelper {
    private final ReviewRepository reviewRepository;

    public Review createReviewAndSave(Application application, CreateReviewRequestDto reviewRequestDto) {
        Review review = Review.createReview(application, reviewRequestDto);
        return reviewRepository.save(review);
    }

    public Review createReviewByLinkAndSave(Long programId, ProgramType programType, CreateReviewRequestDto requestDto) {
        Review review = Review.createReviewByLink(programId, programType, requestDto);
        return reviewRepository.save(review);
    }

    public Review findReviewOrThrow(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public ReviewDetailVo findReviewDetailVoOrThrow(Long reviewId) {
        return reviewRepository.findReviewVo(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public Page<ReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable) {
        return reviewRepository.findChallengeReviewAdminVos(challengeId, pageable);
    }

    public Page<ReviewVo> findChallengeReviewVos(Pageable pageable) {
        return reviewRepository.findChallengeReviewVos(pageable);
    }

    public Page<ReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable) {
        return reviewRepository.findLiveReviewAdminVos(liveId, pageable);
    }

    public Page<ReviewVo> findLiveReviewVos(Pageable pageable) {
        return reviewRepository.findLiveReviewVos(pageable);
    }


}
