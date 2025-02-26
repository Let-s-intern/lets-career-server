package org.letscareer.letscareer.domain.curation.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

@Builder
public record CurationItemVo(
        Long id,
        CurationItemProgramType programType,
        Long programId,
        LocalDateTime programCreateDate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        BlogType category,
        ReportType reportType,
        String tag,
        String title,
        String url,
        String thumbnail
) {
}
