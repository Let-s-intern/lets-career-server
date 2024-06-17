package org.letscareer.letscareer.domain.attendance.service;

import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceCreateRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceUpdateRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {
    void createAttendance(Long missionId, AttendanceCreateRequestDto attendanceCreateRequestDto, Long userId);

    AttendanceAdminListResponseDto getAttendancesOfChallenge(Long challengeId);

    void updateAttendance(Long attendanceId, AttendanceUpdateRequestDto attendanceUpdateRequestDto, User user);
}
