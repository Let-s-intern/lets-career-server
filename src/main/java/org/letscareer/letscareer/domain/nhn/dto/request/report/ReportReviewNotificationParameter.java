package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.entity.Report;

@Builder(access = AccessLevel.PRIVATE)
public record ReportReviewNotificationParameter(
        String userName,
        Long reportId,
        String reportTitle,
        String reportType,
        Long applicationId
) {
    public static ReportReviewNotificationParameter of(String userName,
                                                       Report report,
                                                       Long applicationId) {
        return ReportReviewNotificationParameter.builder()
                .userName(userName)
                .reportId(report.getId())
                .reportTitle(report.getTitle())
                .reportType(report.getType().getDesc())
                .applicationId(applicationId)
                .build();
    }

}
