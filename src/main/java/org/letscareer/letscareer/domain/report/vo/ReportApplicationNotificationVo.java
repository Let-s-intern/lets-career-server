package org.letscareer.letscareer.domain.report.vo;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.report.entity.Report;

import java.time.format.DateTimeFormatter;

@Builder(access = AccessLevel.PRIVATE)
public record ReportApplicationNotificationVo(
        String userName,
        String orderId,
        String orderStatus,
        Integer paymentPrice,
        String reportTitle,
        String reportType,
        String reportOption,
        Boolean isFeedbackApplied,
        String submitDate
) {
    public static ReportApplicationNotificationVo of(String userName,
                                                     Payment payment,
                                                     String orderStatus,
                                                     Report report,
                                                     String reportOption,
                                                     Boolean isFeedbackApplied) {
        return ReportApplicationNotificationVo.builder()
                .userName(userName)
                .orderId(payment.getOrderId())
                .orderStatus(orderStatus)
                .paymentPrice(payment.getFinalPrice())
                .reportTitle(report.getTitle())
                .reportType(report.getType().getDesc())
                .reportOption(reportOption)
                .isFeedbackApplied(isFeedbackApplied)
                .submitDate(payment.getCreateDate().plusDays(7L).format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm")))
                .build();
    }
}
