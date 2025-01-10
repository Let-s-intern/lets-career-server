package org.letscareer.letscareer.domain.report.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ReportRecommendVo(
        Long id,
        ProgramType programType,
        ProgramStatusType programStatusType,
        ReportType reportType,
        String title,
        String notice,
        Boolean isVisible,
        LocalDateTime visibleDate
) {
}
