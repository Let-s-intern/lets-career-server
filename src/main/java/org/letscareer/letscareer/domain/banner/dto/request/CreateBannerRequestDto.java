package org.letscareer.letscareer.domain.banner.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBannerRequestDto(
        @NotNull String title,
        @NotNull String link,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        String imgUrl,
        String contents,
        String colorCode,
        String textColorCode
) {
}
