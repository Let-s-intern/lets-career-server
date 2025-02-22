package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

@Builder
public record CurationItemVo(
        Long id,
        CurationItemProgramType programType,
        Long programId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        ReportType reportType,
        String title,
        String url,
        String thumbnail
) {
}
