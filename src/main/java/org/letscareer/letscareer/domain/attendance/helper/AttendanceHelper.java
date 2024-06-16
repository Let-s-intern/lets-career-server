package org.letscareer.letscareer.domain.attendance.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.request.AttendanceCreateRequestDto;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode;
import org.letscareer.letscareer.domain.attendance.repository.AttendanceRepository;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDailyMissionVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceScoreVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode.CONFLICT_ATTENDANCE;

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

    public AttendanceDailyMissionVo findAttendanceDailyMissionVoOrNull(Long missionId, Long userId) {
        AttendanceDailyMissionVo attendanceDailyMissionVo = attendanceRepository.findAttendanceDailyMissionVo(missionId, userId);
        if(Objects.isNull(attendanceDailyMissionVo)) return new AttendanceDailyMissionVo(null);
        return attendanceDailyMissionVo;
    }

    public void checkExistingAttendance(Long missionId, Long userId) {
        if(attendanceRepository.findAttendanceByMissionIdAndUserId(missionId, userId).isPresent()) {
            throw new ConflictException(CONFLICT_ATTENDANCE);
        }
    }

    public Attendance createAttendanceAndSave(Mission mission, AttendanceCreateRequestDto createRequestDto, AttendanceStatus status, User user) {
        Attendance newAttendance = Attendance.createAttendance(mission, createRequestDto, status, user);
        return attendanceRepository.save(newAttendance);
    }

    public Attendance findAttendanceByMissionIdAndUserIdOrNull(Long missionId, Long userId) {
        return attendanceRepository.findAttendanceByMissionIdAndUserId(missionId, userId).orElse(null);
    }
}
