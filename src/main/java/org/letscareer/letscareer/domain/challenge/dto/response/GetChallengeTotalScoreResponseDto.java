package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeTotalScoreResponseDto(
        Integer currentScore,
        Integer totalScore
) {
    public static GetChallengeTotalScoreResponseDto of(Integer currentScore, Integer totalScore) {
        return GetChallengeTotalScoreResponseDto.builder()
                .currentScore(currentScore)
                .totalScore(totalScore)
                .build();
    }
}
