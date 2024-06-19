package org.letscareer.letscareer.domain.mission.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MissionScoreResponseDto(
        Integer th,
        Integer score
) {
    public static MissionScoreResponseDto of(Integer th, Integer score) {
        return MissionScoreResponseDto.builder()
                .th(th)
                .score(score)
                .build();
    }
}
