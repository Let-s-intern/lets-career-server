package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record ReportDetailVo(
        Long reportId,
        String title,
        String notice,
        String contents,
        ReportType reportType,
        Boolean isVisible,
        LocalDateTime visibleDate
) {
}
