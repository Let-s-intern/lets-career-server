package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.entity.Report;

@Builder(access = AccessLevel.PRIVATE)
public record ReportNotificationParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption
) {
    public static ReportNotificationParameter of(String name,
                                                 Report report,
                                                 String reportOption) {
        return ReportNotificationParameter.builder()
                .name(name)
                .reportTitle(report.getTitle())
                .reportType(report.getType().getDesc())
                .reportOption(reportOption)
                .build();
    }
}
