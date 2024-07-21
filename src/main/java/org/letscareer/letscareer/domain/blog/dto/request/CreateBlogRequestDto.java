package org.letscareer.letscareer.domain.blog.dto.request;

import java.util.List;

public record CreateBlogRequestDto(
        String title,
        String category,
        String thumbnail,
        String description,
        String ctaLink,
        String ctaText,
        List<Long> tagList
) {
}
