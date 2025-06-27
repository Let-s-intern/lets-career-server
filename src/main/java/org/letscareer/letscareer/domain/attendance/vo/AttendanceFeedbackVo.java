package org.letscareer.letscareer.domain.attendance.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.type.AttendanceFeedbackStatus;

@Builder
public record AttendanceFeedbackVo(
        String link,
        AttendanceFeedbackStatus feedbackStatus,
        String feedback,
        String mentorName
) {
}
