package org.letscareer.letscareer.domain.classification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.entity.ChallengeClassification;
import org.letscareer.letscareer.domain.classification.repository.ChallengeClassificationRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class ChallengeClassificationHelper {
    private final ChallengeClassificationRepository challengeClassificationRepository;

    public ChallengeClassification createChallengeClassificationAndSave(CreateChallengeClassificationRequestDto requestDto,
                                                                        Challenge challenge) {
        ChallengeClassification challengeClassification = ChallengeClassification.createChallengeClassification(requestDto, challenge);
        return challengeClassificationRepository.save(challengeClassification);
    }
}
