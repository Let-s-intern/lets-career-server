package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.review.dto.request.CreateReviewRequestDto;
import org.letscareer.letscareer.domain.review.entity.ChallengeReview;
import org.letscareer.letscareer.domain.review.repository.ChallengeReviewRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeReviewHelper {
    private final ChallengeReviewRepository challengeReviewRepository;

    public ChallengeReview createChallengeReviewAndSave(Challenge challenge, ChallengeApplication challengeApplication, CreateReviewRequestDto requestDto) {
        ChallengeReview challengeReview = ChallengeReview.createChallengeReview(challenge, challengeApplication, requestDto);
        return challengeReviewRepository.save(challengeReview);
    }
}
