package org.letscareer.letscareer.domain.curation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreateCurationRequestDto(
        @NotNull String title,
        String subTitle,
        String moreUrl,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        List<CreateCurationItemRequestDto> curationItemList
) {
}
