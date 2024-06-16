package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.challenge.vo.ChallengeScheduleVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeScheduleResponseDto(
        List<ChallengeScheduleVo> scheduleList
) {
    public static GetChallengeScheduleResponseDto of(List<ChallengeScheduleVo> scheduleList) {
        return GetChallengeScheduleResponseDto.builder()
                .scheduleList(scheduleList)
                .build();
    }
}
