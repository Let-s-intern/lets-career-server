package org.letscareer.letscareer.domain.banner.vo;

import java.time.LocalDateTime;

public record PopupUserVo(
        Long id,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isValid,
        String imgUrl
) {
}
