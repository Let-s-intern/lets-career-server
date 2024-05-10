package org.letscareer.letscareer.domain.attendance.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Override
    public AttendanceAdminListResponseDto getAttendancesOfChallenge(String challengeId) {
        return null;
    }
}
