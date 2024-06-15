package org.letscareer.letscareer.domain.mission.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.repository.MissionRepository;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.letscareer.letscareer.domain.mission.error.MissionErrorCode.MISSION_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class MissionHelper {
    private final MissionRepository missionRepository;

    public List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId) {
        return missionRepository.findMissionForChallengeVos(challengeId);
    }

    public Mission findMissionByIdOrThrow(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException(MISSION_NOT_FOUND));
    }

    public void saveMission(Mission mission) {
        missionRepository.save(mission);
    }
}
