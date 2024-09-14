package org.letscareer.letscareer.domain.report.dto.req;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;

import java.time.LocalDateTime;

public record UpdateFeedbackScheduleRequestDto(
        @NotNull(message = "값이 비어있습니다.") ReportFeedbackStatus reportFeedbackStatus,
        ReportDesiredDateType desiredDateType,
        LocalDateTime desiredDateAdmin
) {
}
