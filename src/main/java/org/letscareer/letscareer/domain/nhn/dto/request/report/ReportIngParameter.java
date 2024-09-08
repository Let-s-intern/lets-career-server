package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportIngParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption
) {
    public static ReportIngParameter of(String name,
                                        String reportTitle,
                                        String reportType,
                                        String reportOption) {
        return ReportIngParameter.builder()
                .name(name)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .reportOption(reportOption)
                .build();
    }
}
