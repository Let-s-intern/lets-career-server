package org.letscareer.letscareer.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record TokenReissueRequestDto(
        @NotEmpty String refreshToken
) {
}
