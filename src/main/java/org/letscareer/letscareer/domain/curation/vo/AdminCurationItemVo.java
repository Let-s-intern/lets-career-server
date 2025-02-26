package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

@Builder
public record AdminCurationItemVo(
        Long id,
        CurationItemProgramType programType,
        Long programId,
        LocalDateTime programCreateDate,
        ReportType reportType,
        String tag,
        String title,
        String url,
        String thumbnail
) {
}
