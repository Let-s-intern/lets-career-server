package org.letscareer.letscareer.domain.banner.vo;

import java.time.LocalDateTime;

public record LineBannerAdminDetailVo(
        Long id,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isValid,
        Boolean isVisible,
        String contents,
        String colorCode,
        String textColorCode
) {
}
