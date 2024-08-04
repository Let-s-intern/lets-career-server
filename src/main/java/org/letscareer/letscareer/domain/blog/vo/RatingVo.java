package org.letscareer.letscareer.domain.blog.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.blog.type.BlogType;

import java.time.LocalDateTime;

@Builder
public record RatingVo(
        Long id,
        String title,
        BlogType category,
        String content,
        Integer score,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
