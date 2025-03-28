package org.letscareer.letscareer.domain.blog.vo;

import java.time.LocalDateTime;

public record RatingDetailVo(
        Long id,
        String title,
        String content,
        Integer score,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
