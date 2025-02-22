package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.entity.CurationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurationItemRepository extends JpaRepository<CurationItem, Long>, CurationQueryRepository {
    void deleteAllByCurationId(Long curationId);
}
