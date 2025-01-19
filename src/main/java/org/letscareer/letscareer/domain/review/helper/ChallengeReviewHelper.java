package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.ChallengeReview;
import org.letscareer.letscareer.domain.review.repository.ChallengeReviewRepository;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewAdminVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.review.error.ReviewErrorCode.REVIEW_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ChallengeReviewHelper {
    private final ChallengeReviewRepository challengeReviewRepository;

    public ChallengeReview createChallengeReviewAndSave(Challenge challenge, ChallengeApplication challengeApplication, CreateReviewRequestDto requestDto) {
        ChallengeReview challengeReview = ChallengeReview.createChallengeReview(challenge, challengeApplication, requestDto);
        return challengeReviewRepository.save(challengeReview);
    }

    public ChallengeReview findChallengeReviewByReviewIdOrThrow(Long reviewId) {
        return challengeReviewRepository.findById(reviewId).orElseThrow(() -> new EntityNotFoundException(REVIEW_NOT_FOUND));
    }

    public List<ChallengeReviewAdminVo> findAllChallengeReviewAdminVos() {
        return challengeReviewRepository.findAllChallengeReviewAdminVos();
    }
}
