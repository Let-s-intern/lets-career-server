package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportDoneParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption,
        Long applicationId
) {
    public static ReportDoneParameter of(String name,
                                         String reportTitle,
                                         String reportType,
                                         String reportOption,
                                         Long applicationId) {
        return ReportDoneParameter.builder()
                .name(name)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .reportOption(reportOption)
                .applicationId(applicationId)
                .build();
    }
}
