package org.letscareer.letscareer.domain.blog.vo;

import org.letscareer.letscareer.domain.blog.type.BlogType;

import java.time.LocalDateTime;

public record BlogThumbnailVo(
        Long id,
        String title,
        BlogType category,
        String thumbnail,
        String description,
        Boolean isDisplayed,
        LocalDateTime displayDate,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
