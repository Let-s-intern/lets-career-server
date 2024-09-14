package org.letscareer.letscareer.global.common.utils.slack.dto;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Builder(access = AccessLevel.PRIVATE)
public record ReportWebhookDto(
        Report report,
        ReportApplication reportApplication,
        List<ReportApplicationOption> reportApplicationOptions,
        ReportFeedbackApplication reportFeedbackApplication,
        User user,
        Payment payment
) {
    public static ReportWebhookDto of(Report report,
                                      ReportApplication reportApplication,
                                      List<ReportApplicationOption> reportApplicationOptions,
                                      ReportFeedbackApplication reportFeedbackApplication,
                                      User user,
                                      Payment payment) {
        return ReportWebhookDto.builder()
                .report(report)
                .reportApplication(reportApplication)
                .reportApplicationOptions(reportApplicationOptions)
                .reportFeedbackApplication(reportFeedbackApplication)
                .user(user)
                .payment(payment)
                .build();
    }

    public String getReportTitle() {
        return report.getTitle();
    }

    public String getReportType() {
        return report.getType().getDesc();
    }

    public String isAppliedFeedback() {
        return Objects.isNull(reportFeedbackApplication) ? Boolean.FALSE.toString() : Boolean.TRUE.toString();
    }

    public String getOrderId() {
        return payment.getOrderId();
    }

    public String getUserInfo() {
        return "이름 : " + user.getName() + "\n"
                + "이메일 : " + user.getContactEmail() + "\n"
                + "번호 : " + user.getPhoneNum();
    }

    public String getTimeInfo() {
        LocalDateTime applicationTime = reportApplication.getCreateDate();
        LocalDateTime endDate;
        if (!Objects.isNull(reportApplicationOptions)) {
            endDate = applicationTime.plusDays(5L);
        } else if (ReportPriceType.PREMIUM.equals(reportApplication.getReportPriceType())) {
            endDate = applicationTime.plusDays(3L);
        } else {
            endDate = applicationTime.plusDays(2L);
        }
        return "신청일자 : " + applicationTime + "\n"
                + "마감일자 : " + endDate;
    }
}
