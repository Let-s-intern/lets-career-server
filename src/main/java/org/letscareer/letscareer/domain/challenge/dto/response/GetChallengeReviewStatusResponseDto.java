package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeReviewStatusResponseDto(
        Long reviewId
) {
    public static GetChallengeReviewStatusResponseDto of(Long reviewId) {
        return GetChallengeReviewStatusResponseDto.builder()
                .reviewId(reviewId)
                .build();
    }
}
