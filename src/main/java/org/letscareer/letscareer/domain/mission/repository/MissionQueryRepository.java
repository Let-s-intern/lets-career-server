package org.letscareer.letscareer.domain.mission.repository;

import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.util.List;

public interface MissionQueryRepository {
    List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId);
    List<ContentsMissionVo> findMissionContentsVos(Long missionId, ContentsType contentsType);
}
