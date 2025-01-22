package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeReviewStatusResponseDto(
        Boolean isCompleted
) {
    public static GetChallengeReviewStatusResponseDto of(Boolean isCompleted) {
        return GetChallengeReviewStatusResponseDto.builder()
                .isCompleted(isCompleted)
                .build();
    }
}
