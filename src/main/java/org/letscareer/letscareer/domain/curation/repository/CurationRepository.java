package org.letscareer.letscareer.domain.curation.repository;

import org.letscareer.letscareer.domain.curation.entity.Curation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurationRepository extends JpaRepository<Curation, Long>, CurationQueryRepository {
}
