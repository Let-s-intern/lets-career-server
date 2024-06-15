package org.letscareer.letscareer.domain.mission.service;

import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MissionService {
    void createMission(Long challengeId, CreateMissionRequestDto createMissionRequestDto);

    MissionAdminListResponseDto getMissionsForAdmin(Long challengeId);

    void updateMission(Long missionId, UpdateMissionRequestDto updateMissionRequestDto);
}
