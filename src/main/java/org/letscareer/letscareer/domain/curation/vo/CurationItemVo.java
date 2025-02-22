package org.letscareer.letscareer.domain.curation.vo;

import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;

public record CurationItemVo(
        Long id,
        CurationItemProgramType programType,
        Long programId,
        String title,
        String url,
        String thumbnail
) {
}
