package org.letscareer.letscareer.domain.challlengenotice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.challlengenotice.type.ChallengeNoticeType;

public record CreateChallengeNoticeRequestDto(
        @NotEmpty String title,
        @NotEmpty String link,
        @NotNull ChallengeNoticeType type
) {
}
