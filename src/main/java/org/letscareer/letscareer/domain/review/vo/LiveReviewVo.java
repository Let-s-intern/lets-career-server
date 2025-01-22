package org.letscareer.letscareer.domain.review.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public record LiveReviewVo(
        @JsonIgnore
        Long userId,
        Long reviewId,
        LocalDateTime createDate,
        String title,
        Integer score,
        Integer npsScore,
        String goodPoint,
        String badPoint
) {
}
