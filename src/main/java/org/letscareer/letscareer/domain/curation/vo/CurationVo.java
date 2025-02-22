package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

import java.time.LocalDateTime;

@Builder
public record CurationVo(
    Long curationId,
    CurationLocationType locationType,
    String title,
    String subTitle,
    LocalDateTime startDate,
    LocalDateTime endDate
) {
}
