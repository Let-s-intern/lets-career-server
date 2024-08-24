package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record ReportForAdminVo(
        Long reportId,
        ReportType reportType,
        String title,
        Long applicationCount,
        Long feedbackApplicationCount,
        LocalDateTime visibleDate,
        LocalDateTime createDateTime
) {
}
