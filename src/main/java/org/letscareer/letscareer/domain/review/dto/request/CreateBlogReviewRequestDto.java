package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.program.type.ProgramType;

public record CreateBlogReviewRequestDto(
        @NotNull
        ProgramType programType,
        @NotNull
        String programTitle,
        @NotNull
        String url
) {
}
