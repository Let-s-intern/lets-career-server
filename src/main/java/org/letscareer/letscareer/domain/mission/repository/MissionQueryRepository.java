package org.letscareer.letscareer.domain.mission.repository;

import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.mission.vo.*;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;

import java.util.List;
import java.util.Optional;

public interface MissionQueryRepository {
    List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId);
    Optional<DailyMissionVo> findDailyMissionVoByChallengeId(Long challengeId);
    Optional<MyDailyMissionVo> findMyDailyMissionVoByChallengeId(Long challengeId);
    Optional<MyDailyMissionVo> findMyDailyMissionVoByMissionId(Long missionId);
    List<ContentsMissionVo> findMissionContentsVos(Long missionId, ContentsType contentsType);
    List<MissionScheduleVo> findMissionScheduleVosByChallengeId(Long challengeId);
    List<MySubmittedMissionVo> findMySubmittedMissionVosByChallengeIdAndUserId(Long challengeId, Long userId);
    List<MyMissionVo> findMyMissionVosByChallengeIdAndUserId(Long challengeId, MissionQueryType queryType, Long userId);
}
