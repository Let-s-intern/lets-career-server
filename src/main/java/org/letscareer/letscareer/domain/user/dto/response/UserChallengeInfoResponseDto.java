package org.letscareer.letscareer.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserChallengeInfoResponseDto(
        Boolean pass
) {
    public static UserChallengeInfoResponseDto of(Boolean pass) {
        return UserChallengeInfoResponseDto.builder()
                .pass(pass)
                .build();
    }
}
