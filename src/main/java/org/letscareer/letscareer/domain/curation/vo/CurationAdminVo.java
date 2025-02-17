package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

import java.time.LocalDateTime;

@Builder
public record CurationAdminVo(
    Long curationId,
    CurationLocationType locationType,
    String title,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Boolean isVisible
) {
}
