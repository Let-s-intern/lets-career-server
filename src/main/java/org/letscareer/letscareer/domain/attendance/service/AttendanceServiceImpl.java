package org.letscareer.letscareer.domain.attendance.service;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.attendance.dto.request.CreateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.UpdateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.request.UpdateAttendanceUserRequestDto;
import org.letscareer.letscareer.domain.attendance.dto.response.AttendanceAdminListResponseDto;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.helper.AttendanceHelper;
import org.letscareer.letscareer.domain.attendance.mapper.AttendanceMapper;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;
import org.letscareer.letscareer.domain.score.helper.AttendanceScoreHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.InvalidValueException;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode.ATTENDANCE_NOT_AVAILABLE_DATE;
import static org.letscareer.letscareer.domain.attendance.error.AttendanceErrorCode.ATTENDANCE_UNAUTHORIZED;

@RequiredArgsConstructor
@Transactional
@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;
    private final MissionHelper missionHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final UserHelper userHelper;
    private final AttendanceScoreHelper attendanceScoreHelper;

    @Override
    public void createAttendance(Long missionId, CreateAttendanceRequestDto createRequestDto, Long userId) {
        Mission mission = missionHelper.findMissionByIdOrThrow(missionId);
        Challenge challenge = mission.getChallenge();
        User user = userHelper.findUserByIdOrThrow(userId);
        challengeApplicationHelper.validateChallengeDashboardAccessibleUser(challenge.getId(), user);
        attendanceHelper.checkExistingAttendance(mission.getId(), user.getId());
        AttendanceStatus status = getAttendanceStatus(mission.getStartDate(), mission.getEndDate(), challenge.getEndDate());
        Attendance attendance = attendanceHelper.createAttendanceAndSave(mission, createRequestDto, status, user);
        AttendanceScore attendanceScore = attendanceScoreHelper.createAttendanceScoreAndSave(status, mission.getMissionScore(), attendance);
        attendance.setAttendanceScore(attendanceScore);
        mission.updateAttendanceCount(status);
    }

    @Override
    public AttendanceAdminListResponseDto getAttendancesOfChallenge(Long challengeId) {
        List<AttendanceAdminVo> attendanceAdminList = attendanceHelper.getAttendancesOfChallenge(challengeId);
        return attendanceMapper.toAttendanceAdminListResponseDto(attendanceAdminList);
    }

    @Override
    public void updateAttendance(Long attendanceId, User user, UpdateAttendanceRequestDto updateRequestDto) {
        Attendance attendance = attendanceHelper.findAttendanceByIdOrThrow(attendanceId);
        validateAuthorizedUser(user, attendance);
        updateAttendanceByAdmin(attendance, updateRequestDto);
    }

    @Override
    public void sendLink(Long attendanceId, User user, UpdateAttendanceUserRequestDto link) {
        Attendance attendance = attendanceHelper.findAttendanceByIdOrThrow(attendanceId);
        validateAuthorizedUser(user, attendance);
        updateAttendanceByUser(attendance, link);
    }

    private void validateAuthorizedUser(User user, Attendance attendance) {
        if (user.getRole().equals(UserRole.ADMIN)) return;
        if (!user.getId().equals(attendance.getUser().getId())) {
            throw new UnauthorizedException(ATTENDANCE_UNAUTHORIZED);
        }
    }

    private void updateAttendanceByUser(Attendance attendance, UpdateAttendanceUserRequestDto requestDto) {
        Mission mission = attendance.getMission();
        Challenge challenge = mission.getChallenge();
        AttendanceStatus status = getAttendanceStatus(mission.getStartDate(), mission.getEndDate(), challenge.getEndDate());

        if (isGeneralUpdate(status, attendance)) {
            attendance.updateAttendanceLink(requestDto.link());
        } else if (isReSubmit(status, attendance)) {
            attendance.updateAttendanceLink(requestDto.link());
            attendance.updateAttendanceStatus(AttendanceStatus.UPDATED);
            attendance.updateAttendanceResult(AttendanceResult.WAITING);
        }
    }

    private void updateAttendanceByAdmin(Attendance attendance, UpdateAttendanceRequestDto updateRequestDto) {
        attendance.updateAttendanceAdmin(updateRequestDto);
    }

    private AttendanceStatus getAttendanceStatus(LocalDateTime missionStartDate, LocalDateTime missionEndDate, LocalDateTime challengeEndDate) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(missionStartDate)) {
            throw new InvalidValueException(ATTENDANCE_NOT_AVAILABLE_DATE);
        } else if (now.isBefore(missionEndDate)) {
            return AttendanceStatus.PRESENT;
        } else if (now.isBefore(challengeEndDate.plusDays(2))) {
            return AttendanceStatus.LATE;
        } else {
            throw new InvalidValueException(ATTENDANCE_NOT_AVAILABLE_DATE);
        }
    }

    private boolean isGeneralUpdate(AttendanceStatus status, Attendance attendance) {
        return status.equals(AttendanceStatus.PRESENT) && attendance.getResult().equals(AttendanceResult.WAITING);
    }

    private boolean isReSubmit(AttendanceStatus status, Attendance attendance) {
        return (status.equals(AttendanceStatus.PRESENT) || status.equals(AttendanceStatus.LATE)) && attendance.getResult().equals(AttendanceResult.WRONG);
    }
}
