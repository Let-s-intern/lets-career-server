package org.letscareer.letscareer.domain.missioncontents.repository;

import org.letscareer.letscareer.domain.contents.type.ContentsType;

public interface MissionContentsQueryRepository {
    void deleteAllMissionContentsByMissionIdAndContentsType(Long missionId, ContentsType contentsType);
}
