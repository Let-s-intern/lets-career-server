package org.letscareer.letscareer.domain.classification.repository;

import org.letscareer.letscareer.domain.classification.entity.VodClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VodClassificationRepository extends JpaRepository<VodClassification, Long>, VodClassificationQueryRepository {
}
