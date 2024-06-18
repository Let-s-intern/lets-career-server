package org.letscareer.letscareer.domain.score.repository;

import org.letscareer.letscareer.domain.score.entity.AdminScore;

import java.util.Optional;

public interface AdminScoreQueryRepository {
    Optional<AdminScore> findAdminScoreByChallengeIdAndApplicationId(Long challengeId, Long applicationId);
}
