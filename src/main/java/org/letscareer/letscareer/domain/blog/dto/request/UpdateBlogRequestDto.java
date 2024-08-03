package org.letscareer.letscareer.domain.blog.dto.request;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.blog.type.BlogType;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateBlogRequestDto(
        String title,
        BlogType category,
        String thumbnail,
        String content,
        String description,
        String ctaLink,
        String ctaText,
        Boolean isDisplayed,
        LocalDateTime displayDate,
        List<Long> tagList
) {
}
