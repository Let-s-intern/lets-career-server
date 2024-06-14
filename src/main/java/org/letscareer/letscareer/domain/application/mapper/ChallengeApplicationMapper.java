package org.letscareer.letscareer.domain.application.mapper;

import org.letscareer.letscareer.domain.application.dto.response.GetChallengeApplicationsResponseDto;
import org.letscareer.letscareer.domain.application.vo.AdminChallengeApplicationVo;
import org.letscareer.letscareer.domain.challenge.dto.response.GetChallengeApplicationsScoreResponseDto;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeApplicationMapper {

    public GetChallengeApplicationsResponseDto toGetChallengeApplicationsResponseDto(List<AdminChallengeApplicationVo> vos) {
        return GetChallengeApplicationsResponseDto.of(vos);
    }

    public GetChallengeApplicationsScoreResponseDto toGetChallengeApplicationsScoreResponseDto(List<MissionApplicationScoreResponseDto> missionApplications) {
        return GetChallengeApplicationsScoreResponseDto.of(missionApplications);
    }
}
