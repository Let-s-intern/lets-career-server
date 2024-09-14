package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ReportPaymentParameter(
        String name,
        String orderId,
        String reportTitle,
        Long amount
) {
    public static ReportPaymentParameter of(String name,
                                            String orderId,
                                            String reportTitle,
                                            Long amount) {
        return ReportPaymentParameter.builder()
                .name(name)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .amount(amount)
                .build();
    }
}
