package org.letscareer.letscareer.domain.report.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.report.entity.Report;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ReportApplicationNotificationVo(
        String userName,
        String orderId,
        String orderStatus,
        Integer paymentPrice,
        String reportTitle,
        String reportType,
        String reportOption,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime submitDate
) {
    public static ReportApplicationNotificationVo of(String userName,
                                                     Payment payment,
                                                     String orderStatus,
                                                     Report report,
                                                     String reportOption) {
        return ReportApplicationNotificationVo.builder()
                .userName(userName)
                .orderId(payment.getOrderId())
                .orderStatus(orderStatus)
                .paymentPrice(payment.getFinalPrice())
                .reportTitle(report.getTitle())
                .reportType(report.getType().getDesc())
                .reportOption(reportOption)
                .submitDate(payment.getCreateDate().plusDays(7L))
                .build();
    }
}
