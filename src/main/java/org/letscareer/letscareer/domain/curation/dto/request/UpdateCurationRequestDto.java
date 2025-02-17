package org.letscareer.letscareer.domain.curation.dto.request;

import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

import java.time.LocalDateTime;

public record UpdateCurationRequestDto(
        String title,
        String subTitle,
        Integer listSize,
        String content,
        LocalDateTime startDate,
        LocalDateTime endDate,
        CurationLocationType locationType,
        Boolean isVisible
) {
}
