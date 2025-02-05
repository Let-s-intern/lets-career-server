package org.letscareer.letscareer.domain.review.vo;

import java.time.LocalDateTime;

public record LiveReviewAdminVo(
        Long reviewId,
        LocalDateTime createDate,
        String title,
        String name,
        Integer score,
        Integer npsScore,
        Boolean isVisible
) {
}
