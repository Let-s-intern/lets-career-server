package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportRefundParameter(
        String name,
        String orderId,
        String reportTitle,
        String orderStatus,
        Integer paymentPrice,
        Integer refundPrice,
        Long applicationId
) {
    public static ReportRefundParameter of(String name,
                                           String orderId,
                                           String reportTitle,
                                           String orderStatus,
                                           Integer paymentPrice,
                                           Integer refundPrice,
                                           Long applicationId) {
        return ReportRefundParameter.builder()
                .name(name)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .orderStatus(orderStatus)
                .paymentPrice(paymentPrice)
                .refundPrice(refundPrice)
                .applicationId(applicationId)
                .build();
    }
}
