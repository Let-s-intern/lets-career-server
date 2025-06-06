package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

public record BlogReviewAdminVo(
        Long blogReviewId,
        LocalDateTime postDate,
        ProgramType programType,
        String programTitle,
        String name,
        String title,
        String url,
        String thumbnail,
        Boolean isVisible
) {
}
