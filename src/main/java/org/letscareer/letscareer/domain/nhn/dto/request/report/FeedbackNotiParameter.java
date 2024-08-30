package org.letscareer.letscareer.domain.nhn.dto.request.report;

import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackNotiParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        Long applicationId
) {
    public static FeedbackNotiParameter of(String name,
                                           String reportTitle,
                                           String reportType,
                                           String reportOption,
                                           LocalDateTime desiredDate1,
                                           LocalDateTime desiredDate2,
                                           LocalDateTime desiredDate3,
                                           Long applicationId) {
        return FeedbackNotiParameter.builder()
                .name(name)
                .reportTitle(reportTitle)
                .reportType(reportType)
                .reportOption(reportOption)
                .desiredDate1(desiredDate1)
                .desiredDate2(desiredDate2)
                .desiredDate3(desiredDate3)
                .applicationId(applicationId)
                .build();
    }
}
