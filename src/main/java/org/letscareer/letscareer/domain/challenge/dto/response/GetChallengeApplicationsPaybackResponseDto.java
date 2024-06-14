package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationsPaybackResponseDto(
        List<MissionApplicationScoreResponseDto> missionApplications
) {
    public static GetChallengeApplicationsPaybackResponseDto of(List<MissionApplicationScoreResponseDto> missionApplications) {
        return GetChallengeApplicationsPaybackResponseDto.builder()
                .missionApplications(missionApplications)
                .build();
    }
}
