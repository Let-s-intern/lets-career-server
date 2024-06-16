package org.letscareer.letscareer.domain.mission.repository;

import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.util.List;
import java.util.Optional;

public interface MissionQueryRepository {
    List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId);

    Optional<DailyMissionVo> findDailyMissionVoByChallengeId(Long challengeId);
}
