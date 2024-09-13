package org.letscareer.letscareer.global.common.utils.slack.dto;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.user.entity.User;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ReportWebhookDto(
        String title,
        ReportType reportType,
        String userName,
        LocalDateTime applicationTime
) {
    public static ReportWebhookDto of(Report report, User user, LocalDateTime applicationTime) {
        return ReportWebhookDto.builder()
                .title(report.getTitle())
                .reportType(report.getType())
                .userName(user.getName())
                .applicationTime(applicationTime)
                .build();
    }
}
