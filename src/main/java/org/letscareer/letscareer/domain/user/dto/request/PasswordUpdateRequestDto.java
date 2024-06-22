package org.letscareer.letscareer.domain.user.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record PasswordUpdateRequestDto(
        @NotEmpty String password,
        @NotEmpty String newPassword
) {
}
