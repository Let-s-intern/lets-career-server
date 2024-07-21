package org.letscareer.letscareer.domain.blog.dto.request;

import org.letscareer.letscareer.domain.blog.type.BlogType;

import java.util.List;

public record UpdateBlogRequestDto(
        String title,
        BlogType category,
        String thumbnail,
        String description,
        String ctaLink,
        String ctaText,
        List<Long> tagList
) {
}
