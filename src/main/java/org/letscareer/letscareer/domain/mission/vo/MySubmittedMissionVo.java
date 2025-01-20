package org.letscareer.letscareer.domain.mission.vo;

import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;

public record MySubmittedMissionVo(
        Long id,
        Integer th,
        String title,
        String attendanceLink,
        String attendanceReview,
        AttendanceStatus attendanceStatus,
        AttendanceResult attendanceResult
) {
}
