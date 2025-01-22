package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeGoalResponseDto(
        String goal
) {
    public static GetChallengeGoalResponseDto of(String goal) {
        return GetChallengeGoalResponseDto.builder()
                .goal(goal)
                .build();
    }
}
