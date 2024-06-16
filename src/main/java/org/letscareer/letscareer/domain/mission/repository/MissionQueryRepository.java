package org.letscareer.letscareer.domain.mission.repository;

import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionScheduleVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.util.List;
import java.util.Optional;

public interface MissionQueryRepository {
    List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId);
    Optional<DailyMissionVo> findDailyMissionVoByChallengeId(Long challengeId);
    Optional<MyDailyMissionVo> findMyDailyMissionVoByChallengeId(Long challengeId);
    List<ContentsMissionVo> findMissionContentsVos(Long missionId, ContentsType contentsType);
    List<MissionScheduleVo> findMissionScheduleVosByChallengeId(Long challengeId);
}
