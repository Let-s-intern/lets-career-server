package org.letscareer.letscareer.domain.review.vo;

import java.time.LocalDateTime;

public record LiveReviewVo(
        Long reviewId,
        LocalDateTime createDate,
        String title,
        Integer score,
        Integer npsScore,
        String goodPoint,
        String badPoint
) {
}
