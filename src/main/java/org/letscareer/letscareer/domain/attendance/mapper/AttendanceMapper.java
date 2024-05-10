package org.letscareer.letscareer.domain.attendance.mapper;

import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttendanceMapper {
    public AttendanceAdminListResponseDto toAttendanceAdminListResponseDto(List<AttendanceAdminVo> attendanceAdminList) {
        return AttendanceAdminListResponseDto.of(attendanceAdminList);
    }
}
