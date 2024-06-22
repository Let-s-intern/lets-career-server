package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewVo(
        String name,
        String content,
        Integer score,
        LocalDateTime createdDate
) {
}
