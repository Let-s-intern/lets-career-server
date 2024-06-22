package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeAccessResponseDto(
        Boolean isAccessible
) {
    public static GetChallengeAccessResponseDto of(Boolean isAccessible) {
        return GetChallengeAccessResponseDto.builder()
                .isAccessible(isAccessible)
                .build();
    }
}
