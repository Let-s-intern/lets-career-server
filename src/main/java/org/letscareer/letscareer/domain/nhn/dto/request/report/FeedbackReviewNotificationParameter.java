package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackReviewNotificationParameter(
        String userName,
        String reportTitle,
        String reportType
) {
    public static FeedbackReviewNotificationParameter of(String userName,
                                                         String reportTitle,
                                                         String reportType) {
        return FeedbackReviewNotificationParameter.builder()
                .userName(userName)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .build();
    }
}
