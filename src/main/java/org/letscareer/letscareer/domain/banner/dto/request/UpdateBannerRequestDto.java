package org.letscareer.letscareer.domain.banner.dto.request;

import java.time.LocalDateTime;

public record UpdateBannerRequestDto(
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isValid,
        Boolean isVisible,
        String imgUrl,
        String contents,
        String colorCode,
        String textColorCode
) {
}
