package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeDailyMissionResponseDto(
        DailyMissionVo dailyMission
) {
    public static GetChallengeDailyMissionResponseDto of(DailyMissionVo dailyMission) {
        return GetChallengeDailyMissionResponseDto.builder()
                .dailyMission(dailyMission)
                .build();
    }
}
