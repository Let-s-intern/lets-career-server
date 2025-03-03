package org.letscareer.letscareer.domain.blog.dto.request;

import org.letscareer.letscareer.domain.blog.type.BlogType;

import java.time.LocalDateTime;
import java.util.List;

public record CreateBlogRequestDto(
        String title,
        BlogType category,
        String thumbnail,
        String description,
        String content,
        String ctaLink,
        String ctaText,
        LocalDateTime displayDate,
        Boolean isDisplayed,
        List<Long> tagList
) {
}
