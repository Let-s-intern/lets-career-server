package org.letscareer.letscareer.domain.attendance.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.repository.AttendanceRepository;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Component
public class AttendanceHelper {
    private final AttendanceRepository attendanceRepository;

    public List<AttendanceAdminVo> getAttendancesOfChallenge(Long challengeId) {
        return attendanceRepository.findAllAttendanceByChallengeId(challengeId);
    }
}
