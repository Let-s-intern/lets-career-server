package org.letscareer.letscareer.domain.user.dto.response;

import lombok.Builder;

@Builder
public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {

    public static TokenResponseDto of(String accessToken, String refreshToken) {
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
