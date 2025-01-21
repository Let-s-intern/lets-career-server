package org.letscareer.letscareer.domain.review.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

public record CreateBlogReviewRequestDto(
        @NotNull
        ProgramType programType,
        @NotNull
        String programTitle,
        @NotNull
        String name,
        @NotNull
        String url,
        @NotNull
        LocalDateTime postDate
) {
}
