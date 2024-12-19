package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportReviewNotificationParameter(
        String userName,
        String reportTitle,
        String reportType
) {
    public static ReportReviewNotificationParameter of(String userName,
                                                       String reportTitle,
                                                       String reportType) {
        return ReportReviewNotificationParameter.builder()
                .userName(userName)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .build();
    }

}
