package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.dto.response.MissionApplicationScoreResponseDto;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeApplicationsScoreResponseDto(
        List<MissionApplicationScoreResponseDto> missionApplications
) {
    public static GetChallengeApplicationsScoreResponseDto of(List<MissionApplicationScoreResponseDto> missionApplications) {
        return GetChallengeApplicationsScoreResponseDto.builder()
                .missionApplications(missionApplications)
                .build();
    }
}
