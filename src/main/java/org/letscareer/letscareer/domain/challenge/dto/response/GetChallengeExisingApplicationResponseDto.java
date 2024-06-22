package org.letscareer.letscareer.domain.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record GetChallengeExisingApplicationResponseDto(
        Boolean applied
) {
    public static GetChallengeExisingApplicationResponseDto of(Boolean applied) {
        return GetChallengeExisingApplicationResponseDto.builder()
                .applied(applied)
                .build();
    }
}
