package org.letscareer.letscareer.domain.attendance.service;

import org.letscareer.letscareer.domain.attendance.dto.request.CreateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.UpdateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {
    void createAttendance(Long missionId, CreateAttendanceRequestDto createAttendanceRequestDto, Long userId);

    AttendanceAdminListResponseDto getAttendancesOfChallenge(Long challengeId);

    void updateAttendance(Long attendanceId, User user, UpdateAttendanceRequestDto updateAttendanceRequestDto);
}
