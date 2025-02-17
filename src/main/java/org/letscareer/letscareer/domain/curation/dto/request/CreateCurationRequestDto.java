package org.letscareer.letscareer.domain.curation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateCurationRequestDto(
        @NotNull String title,
        String subTitle,
        @NotNull Integer listSize,
        String content,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate
) {
}
