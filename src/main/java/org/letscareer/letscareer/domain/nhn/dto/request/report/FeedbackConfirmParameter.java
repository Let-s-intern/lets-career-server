package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackConfirmParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        LocalDateTime feedbackDate,
        Long applicationId
) {
    public static FeedbackConfirmParameter of(String name,
                                              String reportTitle,
                                              String reportType,
                                              String reportOption,
                                              LocalDateTime desiredDate1,
                                              LocalDateTime desiredDate2,
                                              LocalDateTime desiredDate3,
                                              LocalDateTime feedbackDate,
                                              Long applicationId) {
        return FeedbackConfirmParameter.builder()
                .name(name)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .reportOption(reportOption)
                .desiredDate1(desiredDate1)
                .desiredDate2(desiredDate2)
                .desiredDate3(desiredDate3)
                .feedbackDate(feedbackDate)
                .applicationId(applicationId)
                .build();
    }
}
