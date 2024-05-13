package org.letscareer.letscareer.domain.application.repository;

import org.letscareer.letscareer.domain.application.entity.ChallengeApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeApplicationRepository extends JpaRepository<ChallengeApplication, Long>, ChallengeApplicationQueryRepository {
}
