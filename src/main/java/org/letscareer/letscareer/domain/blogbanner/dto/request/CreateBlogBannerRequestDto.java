package org.letscareer.letscareer.domain.blogbanner.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateBlogBannerRequestDto(
        @NotNull String title,
        @NotNull String link,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        @NotNull String file,
        @NotNull Integer weight
        ) {
}
