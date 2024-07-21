package org.letscareer.letscareer.domain.blog.vo;

import java.time.LocalDateTime;

public record BlogThumbnailVo(
        Long id,
        String title,
        String category,
        String thumbnail,
        String description,
        LocalDateTime displayDate,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
