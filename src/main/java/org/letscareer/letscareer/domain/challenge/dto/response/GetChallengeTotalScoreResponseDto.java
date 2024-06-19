package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeTotalScoreResponseDto(
        Integer totalScore
) {
    public static GetChallengeTotalScoreResponseDto of(Integer totalScore) {
        return GetChallengeTotalScoreResponseDto.builder()
                .totalScore(totalScore)
                .build();
    }
}
