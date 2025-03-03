package org.letscareer.letscareer.domain.blogbanner.dto.request;

import java.time.LocalDateTime;

public record UpdateBlogBannerRequestDto(
        String title,
        String link,
        Boolean isVisible,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String file
) {
}
