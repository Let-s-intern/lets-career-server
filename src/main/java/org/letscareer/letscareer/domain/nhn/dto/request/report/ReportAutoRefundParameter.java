package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportAutoRefundParameter(
        String name,
        String orderId,
        String reportTitle,
        String orderStatus,
        Integer paymentPrice,
        Integer refundPrice
) {
    public static ReportAutoRefundParameter of(String name,
                                               String orderId,
                                               String reportTitle,
                                               Integer paymentPrice) {
        return ReportAutoRefundParameter.builder()
                .name(name)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .orderStatus("전체취소")
                .paymentPrice(paymentPrice)
                .refundPrice(paymentPrice)
                .build();
    }
}
