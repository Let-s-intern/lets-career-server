package org.letscareer.letscareer.domain.review.dto.request;

import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

public record UpdateBlogReviewRequestDto(
        LocalDateTime postDate,
        ProgramType programType,
        String programTitle,
        String name,
        String title,
        String url,
        Boolean isVisible
) {
}
