package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

@Builder
public record AdminCurationItemVo(
        Long id,
        CurationItemProgramType programType,
        Long programId,
        ReportType reportType,
        String title,
        String url,
        String thumbnail
) {
}
