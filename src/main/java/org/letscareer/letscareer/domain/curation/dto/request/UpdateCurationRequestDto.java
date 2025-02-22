package org.letscareer.letscareer.domain.curation.dto.request;

import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateCurationRequestDto(
        String title,
        String subTitle,
        LocalDateTime startDate,
        LocalDateTime endDate,
        CurationLocationType locationType,
        Boolean isVisible,
        List<CreateCurationItemRequestDto> curationItemList
) {
}
