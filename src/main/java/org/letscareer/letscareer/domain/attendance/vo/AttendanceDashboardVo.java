package org.letscareer.letscareer.domain.attendance.vo;

import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.type.AttendanceFeedbackStatus;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;

public record AttendanceDashboardVo(
        Boolean submitted,
        Long id,
        String link,
        String comments,
        AttendanceStatus status,
        AttendanceResult result,
        AttendanceFeedbackStatus feedbackStatus,
        String review
) {
    public AttendanceDashboardVo(Attendance attendance) {
        this(
                (attendance != null),
                (attendance != null) ? attendance.getId() : null,
                (attendance != null) ? attendance.getLink() : null,
                (attendance != null) ? attendance.getComments() : null,
                (attendance != null) ? attendance.getStatus() : null,
                (attendance != null) ? attendance.getResult() : null,
                (attendance != null) ? attendance.getFeedbackStatus() : null,
                (attendance != null) ? attendance.getReview() : null
        );
    }
}
