package org.letscareer.letscareer.domain.attendance.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode;
import org.letscareer.letscareer.domain.attendance.repository.AttendanceRepository;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceScoreVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AttendanceHelper {
    private final AttendanceRepository attendanceRepository;

    public List<AttendanceAdminVo> getAttendancesOfChallenge(Long challengeId) {
        return attendanceRepository.findAllAttendanceByChallengeId(challengeId);
    }

    public Attendance findAttendanceByIdOrThrow(Long attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new EntityNotFoundException(AttendanceErrorCode.ATTENDANCE_NOT_FOUND));
    }

    public List<AttendanceScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId) {
        return attendanceRepository.findAttendanceScoreVos(applicationId, challengeId);
    }

    public List<MissionAttendanceVo> findMissionAttendanceVo(Long challengeId, Long missionId) {
        return attendanceRepository.findMissionAttendanceVo(challengeId, missionId);
    }
}
