package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record MyReportVo(
        Long reportId,
        Long applicationId,
        String title,
        ReportType reportType,
        ReportPriceType reportPriceType,
        ReportPriceType reportFeedbackPriceType,
        ReportApplicationStatus applicationStatus,
        ReportFeedbackStatus feedbackStatus,
        String reportUrl,
        String applyUrl,
        String recruitmentUrl,
        String zoomLink,
        String zoomPassword,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        LocalDateTime applicationTime,
        LocalDateTime confirmedTime,
        Boolean isCanceled,
        Boolean feedbackIsCanceled
) {
}
