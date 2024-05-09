package org.letscareer.letscareer.domain.contents.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.contents.type.ContentsType;

public record CreateContentsRequestDto(
        @NotNull ContentsType type,
        @NotEmpty String title,
        @NotEmpty String link
) {
}
