package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallengeApplicationRepository extends JpaRepository<ChallengeApplication, Long>, ChallengeApplicationQueryRepository {
    Optional<ChallengeApplication> findChallengeApplicationByChallengeIdAndUserId(Long challengeId, Long userId);
}
