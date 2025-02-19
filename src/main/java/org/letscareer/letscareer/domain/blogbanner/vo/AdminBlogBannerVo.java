package org.letscareer.letscareer.domain.blogbanner.vo;

import java.time.LocalDateTime;

public record AdminBlogBannerVo(
        Long blogBannerId,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer weight,
        Boolean isVisible
) {
}
