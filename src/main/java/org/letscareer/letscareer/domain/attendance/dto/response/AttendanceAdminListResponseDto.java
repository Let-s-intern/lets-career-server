package org.letscareer.letscareer.domain.attendance.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record AttendanceAdminListResponseDto(
        List<AttendanceAdminVo> attendanceList
) {
    public static AttendanceAdminListResponseDto of(List<AttendanceAdminVo> attendanceList) {
        return AttendanceAdminListResponseDto.builder()
                .attendanceList(attendanceList)
                .build();
    }
}
