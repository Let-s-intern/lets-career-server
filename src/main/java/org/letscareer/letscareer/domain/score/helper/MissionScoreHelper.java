package org.letscareer.letscareer.domain.score.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.letscareer.letscareer.domain.score.repository.MissionScoreRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MissionScoreHelper {
    private final MissionScoreRepository missionScoreRepository;

    public MissionScore createMissionScore(CreateMissionRequestDto createMissionRequestDto, Mission newMission) {
        MissionScore missionScore = MissionScore.createMissionScore(createMissionRequestDto, newMission);
        return missionScoreRepository.save(missionScore);
    }
}
