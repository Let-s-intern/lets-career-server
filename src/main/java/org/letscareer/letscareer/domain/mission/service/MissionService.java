package org.letscareer.letscareer.domain.mission.service;

import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MissionService {
    void createMission(CreateMissionRequestDto createMissionRequestDto);
}
