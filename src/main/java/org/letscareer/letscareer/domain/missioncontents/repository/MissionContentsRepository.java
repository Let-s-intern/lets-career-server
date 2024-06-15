package org.letscareer.letscareer.domain.missioncontents.repository;

import org.letscareer.letscareer.domain.missioncontents.entity.MissionContents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionContentsRepository extends JpaRepository<MissionContents, Long>, MissionContentsQueryRepository {
}
