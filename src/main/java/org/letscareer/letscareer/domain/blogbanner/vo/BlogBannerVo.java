package org.letscareer.letscareer.domain.blogbanner.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BlogBannerVo(
        Long blogBannerId,
        String title,
        String link,
        String file,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer weight,
        Boolean isVisible
) {
}
