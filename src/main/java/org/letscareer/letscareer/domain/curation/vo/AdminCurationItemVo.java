package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;

@Builder
public record AdminCurationItemVo(
        Long id,
        CurationItemProgramType programType,
        Long programId,
        String title,
        String url,
        String thumbnail
) {
}
