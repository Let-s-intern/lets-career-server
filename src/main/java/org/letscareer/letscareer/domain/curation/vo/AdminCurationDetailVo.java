package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;

import java.time.LocalDateTime;

@Builder
public record AdminCurationDetailVo(
    Long curationId,
    CurationLocationType locationType,
    String title,
    String subTitle,
    String moreUrl,
    Boolean showImminentList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Boolean isVisible
) {
}
