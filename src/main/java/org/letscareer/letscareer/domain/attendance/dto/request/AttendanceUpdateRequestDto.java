package org.letscareer.letscareer.domain.attendance.dto.request;

import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;

public record AttendanceUpdateRequestDto(
        String link,
        AttendanceStatus status,
        AttendanceResult result,
        String comments
) {
}
