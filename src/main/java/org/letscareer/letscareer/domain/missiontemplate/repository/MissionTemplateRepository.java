package org.letscareer.letscareer.domain.missiontemplate.repository;

import org.letscareer.letscareer.domain.missiontemplate.entity.MissionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionTemplateRepository extends JpaRepository<MissionTemplate, Long>, MissionTemplateQueryRepository {
}
