package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackYetPaymentParameter(
        String userName,
        String orderId,
        String reportTitle,
        Long amount
) {
    public static FeedbackYetPaymentParameter of(String userName,
                                                 String orderId,
                                                 String reportTitle,
                                                 Long amount) {
        return FeedbackYetPaymentParameter.builder()
                .userName(userName)
                .orderId(orderId)
                .reportTitle(reportTitle)
                .amount(amount)
                .build();
    }
}
