package org.letscareer.letscareer.domain.curation.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;

public record CreateCurationItemRequestDto(
        @NotNull CurationItemProgramType programType,
        Long programId,
        String tag,
        String title,
        String url,
        String thumbnail
) {
}
