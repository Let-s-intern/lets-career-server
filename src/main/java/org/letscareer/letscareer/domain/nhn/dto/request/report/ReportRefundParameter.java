package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.payment.type.RefundType;

@Builder(access = AccessLevel.PRIVATE)
public record ReportRefundParameter(
        String name,
        String orderId,
        String reportTitle,
        String orderStatus,
        Integer paymentPrice,
        Integer refundPrice
) {
    public static ReportRefundParameter of(String name,
                                           String orderId,
                                           String reportTitle,
                                           RefundType refundType,
                                           Integer paymentPrice,
                                           Integer refundPrice) {
        return ReportRefundParameter.builder()
                .name(name)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .orderStatus(refundType.equals(RefundType.ALL) ? "전체취소" : "부분취소")
                .paymentPrice(paymentPrice)
                .refundPrice(refundPrice)
                .build();
    }
}
