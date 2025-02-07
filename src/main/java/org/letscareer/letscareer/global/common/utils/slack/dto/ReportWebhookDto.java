package org.letscareer.letscareer.global.common.utils.slack.dto;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.coupon.entity.Coupon;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.type.ReportPaymentStatus;
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
        Payment payment,
        ReportPaymentStatus paymentStatus
) {
    public static ReportWebhookDto of(Report report,
                                      ReportApplication reportApplication,
                                      List<ReportApplicationOption> reportApplicationOptions,
                                      ReportFeedbackApplication reportFeedbackApplication,
                                      User user,
                                      Payment payment,
                                      ReportPaymentStatus paymentStatus) {
        return ReportWebhookDto.builder()
                .report(report)
                .reportApplication(reportApplication)
                .reportApplicationOptions(reportApplicationOptions)
                .reportFeedbackApplication(reportFeedbackApplication)
                .user(user)
                .payment(payment)
                .paymentStatus(paymentStatus)
                .build();
    }

    public String getMainTitle() {
        if(isYet(paymentStatus)) return "진단서 신청 알림";
        else return "서류 제출 알림";
    }

    public String getTimeInfoTitle() {
        if(isYet(paymentStatus)) return "신청일자";
        else return "신청일자 / 서류제출일자 / 진단서 업로드 마감일자 (베이직 : +2일, 프리미엄 : +3일, 옵션추가시 : +5일)";
    }

    private boolean isYet(ReportPaymentStatus paymentStatus) {
        return paymentStatus.equals(ReportPaymentStatus.REPORT_YET) || paymentStatus.equals(ReportPaymentStatus.ALL_YET);
    }

    public String getReportTitle() {
        return report.getTitle();
    }

    public String getReportPriceType() {
        return reportApplication.getReportPriceType().getDesc();
    }

    public String isAppliedFeedback() {
        return Objects.isNull(reportFeedbackApplication) ? Boolean.FALSE.toString() : Boolean.TRUE.toString();
    }

    public String getCouponName() {
        return payment.getCoupon().getName();
    }

    public String getOrderId() {
        return payment.getOrderId();
    }

    public String getUserInfo() {
        return "이름 : " + user.getName() + "\n"
                + "이메일 : " + user.getContactEmail() + "\n"
                + "번호 : " + user.getPhoneNum();
    }

    public String getOptionsString() {
        if (reportApplicationOptions.isEmpty()) return "없음";
        StringBuilder sb = new StringBuilder();
        reportApplicationOptions.forEach(reportApplicationOption -> {
            sb.append(reportApplicationOption.getTitle() + "\n");
        });
        return sb.toString();
    }

    public String getTimeInfo() {
        LocalDateTime applicationTime = reportApplication.getCreateDate();

        if(isYet(paymentStatus)){
            return "신청일자 : " + applicationTime;
        }
        else{
            LocalDateTime appliedDate = reportApplication.getApplyUrlDate();
            LocalDateTime endDate = calculateEndDate(appliedDate);
            return "신청일자 : " + applicationTime + "\n"
                    + "서류제출일자 : " + appliedDate + "\n"
                    + "진단서업로드마감일자 : " + endDate;
        }
    }

    private LocalDateTime calculateEndDate(LocalDateTime applicationTime){
        if (!reportApplicationOptions.isEmpty()) {
            return applicationTime.plusDays(5L);
        } else if (ReportPriceType.PREMIUM.equals(reportApplication.getReportPriceType())) {
            return applicationTime.plusDays(3L);
        } else {
            return applicationTime.plusDays(2L);
        }
    }
}
