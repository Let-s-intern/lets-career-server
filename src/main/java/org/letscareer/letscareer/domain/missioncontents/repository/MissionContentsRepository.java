package org.letscareer.letscareer.domain.missioncontents.repository;

import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.missioncontents.entity.MissionContents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionContentsRepository extends JpaRepository<MissionContents, Long>, MissionContentsQueryRepository {
    List<MissionContents> findAllByMissionIdAndContentsType(Long missionId, ContentsType contentsType);
}
