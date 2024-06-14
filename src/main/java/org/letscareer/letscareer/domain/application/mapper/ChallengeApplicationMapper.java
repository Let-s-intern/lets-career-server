package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeApplicationsPaybackResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeApplicationMapper {

    public GetChallengeApplicationsResponseDto toGetChallengeApplicationsResponseDto(List<AdminChallengeApplicationVo> vos) {
        return GetChallengeApplicationsResponseDto.of(vos);
    }

    public GetChallengeApplicationsPaybackResponseDto toGetChallengeApplicationsScoreResponseDto(List<MissionApplicationScoreResponseDto> missionApplications) {
        return GetChallengeApplicationsPaybackResponseDto.of(missionApplications);
    }
}
