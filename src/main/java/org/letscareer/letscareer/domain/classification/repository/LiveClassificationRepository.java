package org.letscareer.letscareer.domain.classification.repository;

import org.letscareer.letscareer.domain.classification.entity.LiveClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiveClassificationRepository extends JpaRepository<LiveClassification, Long> {
}
