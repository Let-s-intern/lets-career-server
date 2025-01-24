package org.letscareer.letscareer.domain.admincalssification.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.entity.ChallengeAdminClassification;
import org.letscareer.letscareer.domain.admincalssification.repository.ChallengeAdminClassificationRepository;
import org.letscareer.letscareer.domain.admincalssification.request.CreateChallengeAdminClassificationRequestDto;
import org.letscareer.letscareer.domain.admincalssification.vo.ChallengeAdminClassificationDetailVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.classification.error.ChallengeClassificationErrorCode.CHALLENGE_CLASSIFICATION_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class ChallengeAdminClassificationHelper {
    private final ChallengeAdminClassificationRepository challengeClassificationRepository;

    public ChallengeAdminClassification createChallengeClassificationAndSave(CreateChallengeAdminClassificationRequestDto requestDto,
                                                                             Challenge challenge) {
        ChallengeAdminClassification challengeAdminClassification = ChallengeAdminClassification.createChallengeAdminClassification(requestDto, challenge);
        return challengeClassificationRepository.save(challengeAdminClassification);
    }

    public List<ChallengeAdminClassificationDetailVo> findClassificationDetailVos(Long challengeId) {
        return challengeClassificationRepository.findClassificationDetailVos(challengeId);
    }

    public ChallengeAdminClassification findChallengeClassificationByIdOrThrow(Long challengeId) {
        return challengeClassificationRepository.findById(challengeId)
                .orElseThrow(() -> new EntityNotFoundException(CHALLENGE_CLASSIFICATION_NOT_FOUND));
    }

    public void deleteChallengeClassificationsByChallengeId(Long challengeId) {
        challengeClassificationRepository.deleteAllByChallengeId(challengeId);
    }
}
