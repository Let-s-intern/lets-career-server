package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeMyMissionDetailResponseDto(
        MyDailyMissionVo missionInfo
) {
    public static GetChallengeMyMissionDetailResponseDto of(MyDailyMissionVo missionInfo) {
        return GetChallengeMyMissionDetailResponseDto.builder()
                .missionInfo(missionInfo)
                .build();
    }
}
