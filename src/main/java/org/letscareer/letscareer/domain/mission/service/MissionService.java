package org.letscareer.letscareer.domain.mission.service;

import org.letscareer.letscareer.domain.mission.dto.request.CreateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.request.UpdateMissionRequestDto;
import org.letscareer.letscareer.domain.mission.dto.response.FeedbackMissionAdminListResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.GetMissionDetailResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionAdminListResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MissionService {
    GetMissionDetailResponseDto getMissionsDetail(Long missionId);

    MissionAdminListResponseDto getMissionsForAdmin(Long challengeId);

    FeedbackMissionAdminListResponseDto getFeedbackMissionsForAdmin(Long challengeId);

    void createMission(Long challengeId, CreateMissionRequestDto createMissionRequestDto);

    void updateMission(Long missionId, UpdateMissionRequestDto updateMissionRequestDto);

    void deleteMission(Long missionId);
}
