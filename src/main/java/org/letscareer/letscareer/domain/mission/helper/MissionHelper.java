package org.letscareer.letscareer.domain.mission.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.repository.MissionRepository;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MissionHelper {
    private final MissionRepository missionRepository;

    public List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId) {
        return missionRepository.findMissionForChallengeVos(challengeId);
    }

    public void saveMission(Mission mission) {
        missionRepository.save(mission);
    }
}
