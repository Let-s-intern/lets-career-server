package org.letscareer.letscareer.domain.admincalssification.repository;

import org.letscareer.letscareer.domain.admincalssification.entity.ChallengeAdminClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeAdminClassificationRepository extends JpaRepository<ChallengeAdminClassification, Long>, ChallengeAdminClassificationQueryRepository {
    void deleteAllByChallengeId(Long challengeId);
}
