package org.letscareer.letscareer.domain.report.dto.req;

import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;

import java.time.LocalDateTime;

public record UpdateFeedbackScheduleRequestDto(
        ReportDesiredDateType desiredDateType,
        LocalDateTime desiredDateAdmin
) {
}
