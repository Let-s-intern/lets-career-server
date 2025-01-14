package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportType;

public record ReportDetailVo(
        Long reportId,
        String title,
        String notice,
        String contents,
        ReportType reportType,
        Boolean isVisible
) {
}
