package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record MyReportFeedbackVo(
        Long reportId,
        Long applicationId,
        String title,
        ReportType type,
        ReportFeedbackStatus reportFeedbackStatus,
        String zoomLink,
        String zoomPassword,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        LocalDateTime applicationTime,
        LocalDateTime confirmedTime
) {
}
