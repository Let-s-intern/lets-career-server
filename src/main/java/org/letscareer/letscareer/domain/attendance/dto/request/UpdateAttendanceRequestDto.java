package org.letscareer.letscareer.domain.attendance.dto.request;

import org.letscareer.letscareer.domain.attendance.type.AttendanceFeedbackStatus;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;

public record UpdateAttendanceRequestDto(
        String link,
        AttendanceStatus status,
        AttendanceResult result,
        String comments,
        String review,
        Boolean reviewIsVisible,
        Long mentorUserId,
        String feedback,
        AttendanceFeedbackStatus feedbackStatus
) {
}
