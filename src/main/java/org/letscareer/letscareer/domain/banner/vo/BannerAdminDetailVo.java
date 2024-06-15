package org.letscareer.letscareer.domain.banner.vo;

import java.time.LocalDateTime;

public record BannerAdminDetailVo(
        Long id,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isValid,
        Boolean isVisible,
        String imgUrl
) {
}
