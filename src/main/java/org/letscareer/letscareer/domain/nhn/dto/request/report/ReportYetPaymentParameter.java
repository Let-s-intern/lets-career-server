package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportYetPaymentParameter(
        String userName,
        String orderId,
        String reportTitle,
        Long amount
) {
    public static ReportYetPaymentParameter of(String userName,
                                               String orderId,
                                               String reportTitle,
                                               Long amount) {
        return ReportYetPaymentParameter.builder()
                .userName(userName)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .amount(amount)
                .build();
    }
}
