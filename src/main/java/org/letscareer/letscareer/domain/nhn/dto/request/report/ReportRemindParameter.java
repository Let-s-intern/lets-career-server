package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationNotificationVo;

@Builder(access = AccessLevel.PRIVATE)
public record ReportRemindParameter(
        String userName,
        String reportTitle,
        String reportType,
        String reportOption,
        String submitDate
) {
    public static ReportRemindParameter of(ReportApplicationNotificationVo notificationVo) {
        return ReportRemindParameter.builder()
                .userName(notificationVo.userName())
                .reportTitle(notificationVo.reportTitle())
                .reportType(notificationVo.reportType())
                .reportOption(notificationVo.reportOption())
                .submitDate(notificationVo.submitDate())
                .build();
    }
}
