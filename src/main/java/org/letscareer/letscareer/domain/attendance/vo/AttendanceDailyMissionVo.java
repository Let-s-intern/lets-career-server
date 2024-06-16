package org.letscareer.letscareer.domain.attendance.vo;

import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;

public record AttendanceDailyMissionVo(
        Boolean submitted,
        Long id,
        String link,
        AttendanceStatus status,
        AttendanceResult result
) {
    public AttendanceDailyMissionVo(Attendance attendance) {
        this(
                (attendance != null),
                (attendance != null) ? attendance.getId() : null,
                (attendance != null) ? attendance.getLink() : null,
                (attendance != null) ? attendance.getStatus() : null,
                (attendance != null) ? attendance.getResult() : null
        );
    }
}
