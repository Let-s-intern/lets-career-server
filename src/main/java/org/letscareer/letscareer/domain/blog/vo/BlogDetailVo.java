package org.letscareer.letscareer.domain.blog.vo;

import org.letscareer.letscareer.domain.blog.type.BlogType;

import java.time.LocalDateTime;

public record BlogDetailVo(
        Long id,
        String title,
        BlogType category,
        String thumbnail,
        String description,
        String content,
        String ctaLink,
        String ctaText,
        Boolean isDisplayed,
        LocalDateTime displayDate,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate,
        Integer likeCount
) {
}
