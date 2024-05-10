package org.letscareer.letscareer.domain.mission.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.repository.MissionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class MissionHelper {
    private final MissionRepository missionRepository;

    public void saveMission(Mission mission) {
        missionRepository.save(mission);
    }
}
