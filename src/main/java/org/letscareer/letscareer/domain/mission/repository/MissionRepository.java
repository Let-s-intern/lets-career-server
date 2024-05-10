package org.letscareer.letscareer.domain.mission.repository;

import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionQueryRepository {
}
