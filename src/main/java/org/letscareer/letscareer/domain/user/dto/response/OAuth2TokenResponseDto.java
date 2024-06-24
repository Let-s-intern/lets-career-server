package org.letscareer.letscareer.domain.user.dto.response;

import lombok.Builder;

@Builder
public record OAuth2TokenResponseDto(
        String accessToken,
        String refreshToken,
        Boolean isNew
) {

    public static OAuth2TokenResponseDto of(String accessToken, String refreshToken, Boolean isNew) {
        return OAuth2TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isNew(isNew)
                .build();
    }
}
