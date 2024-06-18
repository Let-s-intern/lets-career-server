package org.letscareer.letscareer.domain.banner.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PopupBannerAdminDetailVo(
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
