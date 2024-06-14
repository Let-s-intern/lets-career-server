package org.letscareer.letscareer.domain.score.helper;

import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.springframework.stereotype.Component;

@Component
public class MissionScoreHelper {

    public MissionScore createMissionScore(CreateMissionRequestDto createMissionRequestDto, Mission newMission) {
        MissionScore missionScore = MissionScore.createMissionScore(createMissionRequestDto, newMission);
        return null;
    }
}
