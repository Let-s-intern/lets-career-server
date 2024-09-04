package org.letscareer.letscareer.domain.nhn.dto.request.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.report.entity.Report;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackDdayParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime feedbackDate,
        String zoomLink,
        Long applicationId
) {
    public static FeedbackDdayParameter of(String name,
                                           Report report,
                                           String reportOption,
                                           ReportFeedbackApplication reportFeedbackApplication) {
        return FeedbackDdayParameter.builder()
                .name(name)
                .reportTitle(report.getTitle())
                .reportType(report.getType().getDesc())
                .reportOption(reportOption)
                .feedbackDate(reportFeedbackApplication.getFeedbackDate())
                .zoomLink(reportFeedbackApplication.getZoomLink())
                .build();
    }
}
