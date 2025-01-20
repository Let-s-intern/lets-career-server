package org.letscareer.letscareer.domain.review.vo.old;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OldReviewVo(
        Long id,
        String name,
        String content,
        Integer score,
        LocalDateTime createdDate
) {
}
