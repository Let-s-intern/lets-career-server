package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportPaymentParameter(
        String name,
        String orderId,
        String reportTitle,
        Long amount,
        Long applicationId
) {
    public static ReportPaymentParameter of(String name,
                                            String orderId,
                                            String reportTitle,
                                            Long amount,
                                            Long applicationId) {
        return ReportPaymentParameter.builder()
                .name(name)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .amount(amount)
                .applicationId(applicationId)
                .build();
    }
}
