package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.mission.vo.MissionScheduleVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeScheduleResponseDto(
        List<MissionScheduleVo> missionList
) {
    public static GetChallengeScheduleResponseDto of(List<MissionScheduleVo> missionList) {
        return GetChallengeScheduleResponseDto.builder()
                .missionList(missionList)
                .build();
    }
}
