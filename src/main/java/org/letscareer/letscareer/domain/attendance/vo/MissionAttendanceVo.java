package org.letscareer.letscareer.domain.attendance.vo;

import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.price.type.ChallengePricePlanType;
import org.letscareer.letscareer.domain.user.type.AccountType;

import java.time.LocalDateTime;

public record MissionAttendanceVo(
        Long id,
        String name,
        String email,
        AttendanceStatus status,
        String link,
        AttendanceResult result,
        String comments,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate,
        String review,
        Boolean reviewIsVisible,
        ChallengePricePlanType challengePricePlanType
) {
}
