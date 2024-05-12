package org.letscareer.letscareer.domain.challengeguide.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challengeguide.entity.ChallengeGuide;
import org.letscareer.letscareer.domain.challengeguide.repository.ChallengeGuideRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ChallengeGuideHelper {
    private final ChallengeGuideRepository challengeGuideRepository;

    public void saveChallengeGuide(ChallengeGuide challengeGuide) {
        challengeGuideRepository.save(challengeGuide);
    }
}
