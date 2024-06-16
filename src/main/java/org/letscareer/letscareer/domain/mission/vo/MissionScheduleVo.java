package org.letscareer.letscareer.domain.mission.vo;

import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.mission.type.MissionStatusType;

import java.time.LocalDateTime;

public record MissionScheduleVo(
        Long id,
        Integer th,
        LocalDateTime startDate,
        LocalDateTime endDate,
        MissionStatusType status,
        Boolean submitted,
        AttendanceStatus attendanceStatus,
        AttendanceResult attendanceResult
) {
    public MissionScheduleVo(Long id,
                             Integer th,
                             LocalDateTime startDate,
                             LocalDateTime endDate,
                             MissionStatusType status,
                             Attendance attendance) {
        this(
                id,
                th,
                startDate,
                endDate,
                status,
                (attendance != null),
                (attendance != null) ? attendance.getStatus() : null,
                (attendance != null) ? attendance.getResult() : null
        );
    }
}
