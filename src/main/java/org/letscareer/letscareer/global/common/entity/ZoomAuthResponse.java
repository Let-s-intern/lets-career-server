package org.letscareer.letscareer.global.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ZoomAuthResponse(
        @JsonProperty(value = "access_token") String accessToken,
        @JsonProperty(value = "token_type") String tokenType,
        @JsonProperty(value = "expires_in") Long expiresIn,
        String scope
) {
}
