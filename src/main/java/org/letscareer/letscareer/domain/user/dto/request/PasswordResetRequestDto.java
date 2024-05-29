package org.letscareer.letscareer.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record PasswordResetRequestDto(
        @NotEmpty String name,
        @NotEmpty String email
) {
}
