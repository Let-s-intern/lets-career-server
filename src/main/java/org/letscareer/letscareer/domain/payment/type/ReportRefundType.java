package org.letscareer.letscareer.domain.payment.type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.payment.entity.Payment;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ReportRefundType {

    ZERO(0),
    ALL(1),
    PERCENT_80(0.8),
    PERCENT_50(0.5);

    private final double percent;

    public static ReportRefundType ofReport(ReportApplication reportApplication, Payment payment) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime paymentCreateDate = payment.getCreateDate();
        ReportApplicationStatus reportApplicationStatus = reportApplication.getStatus();
        if(now.isBefore(paymentCreateDate.plusMinutes(20L))) {
            return switch (reportApplicationStatus) {
                case APPLIED -> ALL;
                case REPORTING, REPORTED -> PERCENT_80;
                case COMPLETED -> ZERO;
            };
        } else if(!reportApplicationStatus.equals(ReportApplicationStatus.COMPLETED)) {
            return PERCENT_80;
        } else {
            return ZERO;
        }
    }

    public static ReportRefundType ofFeedback(ReportFeedbackApplication reportFeedbackApplication) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime feedbackDate = reportFeedbackApplication.getFeedbackDate();
        ReportFeedbackStatus reportFeedbackStatus = reportFeedbackApplication.getReportFeedbackStatus();
        if(reportFeedbackStatus.equals(ReportFeedbackStatus.APPLIED) || reportFeedbackStatus.equals(ReportFeedbackStatus.PENDING)) return ALL;
        else if(reportFeedbackStatus.equals(ReportFeedbackStatus.CONFIRMED) && now.isBefore(feedbackDate.minusDays(1L))) return PERCENT_80;
        else if(now.isAfter(feedbackDate.minusDays(1L)) && now.isBefore(feedbackDate)) return PERCENT_50;
        return ZERO;
    }
}
