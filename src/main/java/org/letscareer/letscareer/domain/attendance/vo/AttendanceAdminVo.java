package org.letscareer.letscareer.domain.attendance.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.user.type.AccountType;

@Builder
public record AttendanceAdminVo(
        Long id,
        String name,
        String email,
        AccountType accountType,
        String accountNum,
        String accountOwner,
        AttendanceStatus status,
        String link,
        AttendanceResult result,
        Boolean isRefunded,
        String comment
) {
}
