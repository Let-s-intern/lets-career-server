package org.letscareer.letscareer.domain.blog.vo;

import java.time.LocalDateTime;

public record BlogDetailVo(
        Long id,
        String title,
        String category,
        String thumbnail,
        String description,
        String content,
        String ctaLink,
        String ctaText,
        String subContent,
        String subCtaLink,
        String subCtaText,
        LocalDateTime displayDate,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
