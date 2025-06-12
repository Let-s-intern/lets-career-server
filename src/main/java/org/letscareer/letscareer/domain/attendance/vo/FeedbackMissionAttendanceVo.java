package org.letscareer.letscareer.domain.attendance.vo;

import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;

public record FeedbackMissionAttendanceVo(
        Long id,
        String mentorName,
        String name,
        String major,
        String wishJob,
        String wishCompany,
        String link,
        AttendanceStatus status,
        AttendanceResult result,
        ChallengePricePlanType challengePricePlanType
) {
}
