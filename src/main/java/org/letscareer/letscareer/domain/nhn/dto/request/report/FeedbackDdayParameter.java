package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackDdayParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption,
        LocalDateTime feedbackDate,
        String zoomLink,
        Long applicationId
) {
    public static FeedbackDdayParameter of(String name,
                                           String reportTitle,
                                           String reportType,
                                           String reportOption,
                                           LocalDateTime feedbackDate,
                                           String zoomLink,
                                           Long applicationId) {
        return FeedbackDdayParameter.builder()
                .name(name)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .reportOption(reportOption)
                .feedbackDate(feedbackDate)
                .zoomLink(zoomLink)
                .applicationId(applicationId)
                .build();
    }
}
