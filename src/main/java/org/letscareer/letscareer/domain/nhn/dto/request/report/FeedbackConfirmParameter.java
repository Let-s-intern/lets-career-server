package org.letscareer.letscareer.domain.nhn.dto.request.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.report.entity.Report;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record FeedbackConfirmParameter(
        String name,
        String reportTitle,
        String reportType,
        String reportOption,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime desiredDate1,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime desiredDate2,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime desiredDate3,
        @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm", timezone = "Asia/Seoul")
        LocalDateTime feedbackDate,
        Long applicationId
) {
    public static FeedbackConfirmParameter of(String name,
                                              Report report,
                                              String reportOption,
                                              ReportFeedbackApplication reportFeedbackApplication) {
        return FeedbackConfirmParameter.builder()
                .name(name)
                .reportTitle(report.getTitle())
                .reportType(report.getType().getDesc())
                .reportOption(reportOption)
                .desiredDate1(reportFeedbackApplication.getDesiredDate1())
                .desiredDate2(reportFeedbackApplication.getDesiredDate2())
                .desiredDate3(reportFeedbackApplication.getDesiredDate3())
                .feedbackDate(reportFeedbackApplication.getCheckedFeedbackDate(reportFeedbackApplication.getDesiredDateType()))
                .build();
    }
}
