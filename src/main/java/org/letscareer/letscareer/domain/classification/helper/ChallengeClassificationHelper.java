package org.letscareer.letscareer.domain.classification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.classification.dto.request.CreateChallengeClassificationRequestDto;
import org.letscareer.letscareer.domain.classification.entity.ChallengeClassification;
import org.letscareer.letscareer.domain.classification.repository.ChallengeClassificationRepository;
import org.letscareer.letscareer.domain.classification.vo.ChallengeClassificationDetailVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.letscareer.letscareer.domain.classification.error.ChallengeErrorCode.CHALLENGE_CLASSIFICATION_NOT_FOUND;

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

    public List<ChallengeClassificationDetailVo> findClassificationDetailVos(Long challengeId) {
        return challengeClassificationRepository.findClassificationDetailVos(challengeId);
    }

    public ChallengeClassification findChallengeClassificationByIdOrThrow(Long challengeId) {
        return challengeClassificationRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_CLASSIFICATION_NOT_FOUND));
    }

    public void deleteChallengeClassificationsByChallengeId(Long challengeId) {
        challengeClassificationRepository.deleteAllByChallengeId(challengeId);
    }
}
