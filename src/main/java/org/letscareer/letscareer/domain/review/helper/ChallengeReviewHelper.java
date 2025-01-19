package org.letscareer.letscareer.domain.review.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.repository.ChallengeRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeReviewHelper {
    private final ChallengeRepository challengeRepository;
}
