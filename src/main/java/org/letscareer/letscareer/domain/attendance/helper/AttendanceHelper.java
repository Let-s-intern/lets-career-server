package org.letscareer.letscareer.domain.attendance.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.dto.request.CreateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode;
import org.letscareer.letscareer.domain.attendance.repository.AttendanceRepository;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.vo.*;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode.ATTENDANCE_NOT_FOUND;
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

    public List<MissionScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId) {
        return attendanceRepository.findAttendanceScoreVos(applicationId, challengeId);
    }

    public List<MissionAttendanceWithOptionsVo> findMissionAttendanceVos(Long challengeId, Long missionId) {
        return attendanceRepository.findMissionAttendanceVos(challengeId, missionId);
    }

    public List<FeedbackMissionAttendanceVo> findFeedbackMissionAttendanceVos(Long mentorId, Long challengeId, Long missionId, Long challengeOptionId) {
        return attendanceRepository.findFeedbackMissionAttendanceVos(mentorId, challengeId, missionId, challengeOptionId);
    }

    public FeedbackMissionAttendanceDetailVo findFeedbackMissionAttendanceDetailVoByAttendanceIdOrElseThrow(Long attendanceId) {
        return attendanceRepository.findFeedbackMissionAttendanceDetailVoByAttendanceId(attendanceId).orElseThrow(() -> new EntityNotFoundException(ATTENDANCE_NOT_FOUND));
    }

    public AttendanceDashboardVo findAttendanceDashboardVoOrNull(Long missionId, Long userId) {
        AttendanceDashboardVo attendanceDashboardVo = attendanceRepository.findAttendanceDashboardVo(missionId, userId);
        if(Objects.isNull(attendanceDashboardVo)) return new AttendanceDashboardVo(null);
        return attendanceDashboardVo;
    }

    public AttendanceFeedbackVo findAttendanceFeedbackVoOrNull(Long missionId, Long userId) {
        return attendanceRepository.findAttendanceFeedbackVo(missionId, userId);
    }

    public void checkExistingAttendance(Long missionId, Long userId) {
        if(attendanceRepository.findAttendanceByMissionIdAndUserId(missionId, userId).isPresent()) {
            throw new ConflictException(CONFLICT_ATTENDANCE);
        }
    }

    public Attendance createAttendanceAndSave(Mission mission, CreateAttendanceRequestDto createRequestDto,
            AttendanceStatus status, User user) {
        AttendanceResult result = (mission.getTh() == 0) ? AttendanceResult.PASS : AttendanceResult.WAITING;
        Attendance newAttendance = Attendance.createAttendance(mission, createRequestDto, status, user, result);
        return attendanceRepository.save(newAttendance);
    }

    public Attendance findAttendanceByMissionIdAndUserIdOrNull(Long missionId, Long userId) {
        return attendanceRepository.findAttendanceByMissionIdAndUserId(missionId, userId).orElse(null);
    }

    public List<MissionReviewAdminVo> findAllMissionReviewAdminVos() {
        return attendanceRepository.findAllMissionReviewAdminVos();
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public boolean isOTCompleted(Long challengeId, Long userId) {
        return attendanceRepository.existsByMissionChallengeIdAndMissionThAndUserIdAndResult(challengeId, 0, userId,
                AttendanceResult.PASS);
    }
}
