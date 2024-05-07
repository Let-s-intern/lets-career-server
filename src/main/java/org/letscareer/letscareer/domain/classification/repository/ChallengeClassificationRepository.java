package org.letscareer.letscareer.domain.classification.repository;

import org.letscareer.letscareer.domain.classification.entity.ChallengeClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeClassificationRepository extends JpaRepository<ChallengeClassification, Long> {
}
