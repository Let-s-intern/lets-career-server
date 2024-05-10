package org.letscareer.letscareer.domain.attendance.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.helper.AttendanceHelper;
import org.letscareer.letscareer.domain.attendance.mapper.AttendanceMapper;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;
    @Override
    public AttendanceAdminListResponseDto getAttendancesOfChallenge(Long challengeId) {
        List<AttendanceAdminVo> attendanceAdminList = attendanceHelper.getAttendancesOfChallenge(challengeId);
        return attendanceMapper.toAttendanceAdminListResponseDto(attendanceAdminList);
    }
}
