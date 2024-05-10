package org.letscareer.letscareer.domain.attendance.service;

import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AttendanceService {
    AttendanceAdminListResponseDto getAttendancesOfChallenge(Long challengeId);
}
