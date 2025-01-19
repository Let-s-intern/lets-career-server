package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;

public record UpdateReviewRequestDto(
        @NotNull
        ReviewProgramType type,
        Boolean isVisible
) {
}
