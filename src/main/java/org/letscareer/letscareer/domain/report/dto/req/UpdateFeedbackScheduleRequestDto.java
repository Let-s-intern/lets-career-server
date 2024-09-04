package org.letscareer.letscareer.domain.report.dto.req;

import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;

import java.time.LocalDateTime;

public record UpdateFeedbackScheduleRequestDto(
        ReportFeedbackStatus reportFeedbackStatus,
        ReportDesiredDateType desiredDateType,
        LocalDateTime desiredDateAdmin
) {
}
