package org.letscareer.letscareer.domain.challengeguide.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateChallengeGuideRequestDto(
        @NotEmpty String title,
        @NotEmpty String link
) {
}
