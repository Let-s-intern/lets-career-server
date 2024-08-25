package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record MyReportVo(
        Long reportId,
        Long applicationId,
        String title,
        ReportType type,
        ReportApplicationStatus reportApplicationStatus,
        LocalDateTime applicationTime,
        LocalDateTime completedTime
) {
}
