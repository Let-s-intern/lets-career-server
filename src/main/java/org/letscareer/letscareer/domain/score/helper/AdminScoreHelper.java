package org.letscareer.letscareer.domain.score.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.letscareer.letscareer.domain.score.entity.AdminScore;
import org.letscareer.letscareer.domain.score.repository.AdminScoreRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminScoreHelper {
    private final AdminScoreRepository adminScoreRepository;

    public AdminScore createAdminScoreAndSave(ChallengeApplication application) {
        AdminScore adminScore = AdminScore.creatAdminScore(application);
        return adminScoreRepository.save(adminScore);
    }

    public AdminScore findAdminScoreByChallengeIdAndApplicationIdOrThrow(Long challengeId, Long applicationId) {
        return adminScoreRepository.findAdminScoreByChallengeIdAndApplicationId(challengeId, applicationId)
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
