package org.letscareer.letscareer.domain.attendance.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.letscareer.letscareer.domain.attendance.dto.request.CreateAttendanceRequestDto;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.repository.AttendanceRepository;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.error.exception.ConflictException;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AttendanceHelperTest {
    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceHelper attendanceHelper;

    @Test
    @DisplayName("0회차 미션 완료 여부 확인 - 완료된 경우")
    void isOtCompleted_whenCompleted_ReturnsTrue() {
        // Given
        Long challengeId = 1L;
        Long userId = 100L;

        when(attendanceRepository.existsByMissionChallengeIdAndMissionThAndUserIdAndResult(challengeId, 0, userId,
                AttendanceResult.PASS)).thenReturn(true);

        // When
        boolean result = attendanceHelper.isOTCompleted(challengeId, userId);

        // Then
        assertThat(result).isTrue();
        verify(attendanceRepository).existsByMissionChallengeIdAndMissionThAndUserIdAndResult(
                challengeId, 0, userId, AttendanceResult.PASS);
    }

    @Test
    @DisplayName("0회차 미션 완료 여부 확인 - 완료되지 않은 경우")
    void isOtCompleted_whenNotCompleted_ReturnsFalse() {
        // Given
        Long challengeId = 1L;
        Long userId = 100L;

        when(attendanceRepository.existsByMissionChallengeIdAndMissionThAndUserIdAndResult(challengeId, 0, userId,
                AttendanceResult.PASS)).thenReturn(false);

        // When
        boolean result = attendanceHelper.isOTCompleted(challengeId, userId);

        // Then
        assertThat(result).isFalse();
        verify(attendanceRepository).existsByMissionChallengeIdAndMissionThAndUserIdAndResult(
                challengeId, 0, userId, AttendanceResult.PASS);
    }

    @Test
    @DisplayName("출석 생성 및 저장 - 0회차 미션인 경우 자동 승인")
    void createAttendanceAndSave_WhenOTMission_AutoApprove() {
        // Given
        Mission otMission = createTestMission(0);
        User user = createTestUser();
        CreateAttendanceRequestDto requestDto = new CreateAttendanceRequestDto("test-link", "test-review");
        AttendanceStatus status = AttendanceStatus.PRESENT;

        Attendance expectedAttendance = Attendance.createAttendance(otMission, requestDto, status, user, AttendanceResult.PASS);

        when(attendanceRepository.save(any(Attendance.class)))
                .thenReturn(expectedAttendance);

        // When
        Attendance result = attendanceHelper.createAttendanceAndSave(otMission, requestDto, status, user);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getResult()).isEqualTo(AttendanceResult.PASS);
        verify(attendanceRepository).save(any(Attendance.class));
    }

    @Test
    @DisplayName("출석 생성 및 저장 - 일반 미션인 경우 대기 상태")
    void createAttendanceAndSave_WhenNormalMission_WaitingStatus() {
        // Given
        Mission normalMission = createTestMission(1);
        User user = createTestUser();
        CreateAttendanceRequestDto requestDto = new CreateAttendanceRequestDto("test-link", "test-review");
        AttendanceStatus status = AttendanceStatus.PRESENT;

        Attendance expectedAttendance = Attendance.createAttendance(normalMission, requestDto, status, user, AttendanceResult.WAITING);

        when(attendanceRepository.save(any(Attendance.class)))
                .thenReturn(expectedAttendance);

        // When
        Attendance result = attendanceHelper.createAttendanceAndSave(normalMission, requestDto, status, user);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getResult()).isEqualTo(AttendanceResult.WAITING);
        verify(attendanceRepository).save(any(Attendance.class));
    }

    @Test
    @DisplayName("기존 출석 확인 - 이미 존재하는 경우 예외 발생")
    void checkExistingAttendance_WhenExists_ThrowsException() {
        // Given
        Long missionId = 1L;
        Long userId = 100L;
        Attendance existingAttendance = mock(Attendance.class);

        when(attendanceRepository.findAttendanceByMissionIdAndUserId(missionId, userId))
                .thenReturn(Optional.of(existingAttendance));

        // When & Then
        assertThatThrownBy(() -> attendanceHelper.checkExistingAttendance(missionId, userId))
                .isInstanceOf(ConflictException.class);

        verify(attendanceRepository).findAttendanceByMissionIdAndUserId(missionId, userId);
    }

    @Test
    @DisplayName("기존 출석 확인 - 존재하지 않는 경우 정상")
    void checkExistingAttendance_WhenNotExists_DoesNotThrow() {
        // Given
        Long missionId = 1L;
        Long userId = 100L;

        when(attendanceRepository.findAttendanceByMissionIdAndUserId(missionId, userId))
                .thenReturn(Optional.empty());

        // When & Then
        attendanceHelper.checkExistingAttendance(missionId, userId);

        verify(attendanceRepository).findAttendanceByMissionIdAndUserId(missionId, userId);
    }

    @Test
    @DisplayName("ID로 출석 조회 - 존재하는 경우")
    void findAttendanceByIdOrThrow_WhenExists_ReturnsAttendance() {
        // Given
        Long attendanceId = 1L;
        Attendance expectedAttendance = mock(Attendance.class);

        when(attendanceRepository.findById(attendanceId))
                .thenReturn(Optional.of(expectedAttendance));

        // When
        Attendance result = attendanceHelper.findAttendanceByIdOrThrow(attendanceId);

        // Then
        assertThat(result).isEqualTo(expectedAttendance);
        verify(attendanceRepository).findById(attendanceId);
    }

    @Test
    @DisplayName("ID로 출석 조회 - 존재하지 않는 경우 예외 발생")
    void findAttendanceByIdOrThrow_WhenNotExists_ThrowsException() {
        // Given
        Long attendanceId = 1L;

        when(attendanceRepository.findById(attendanceId))
                .thenReturn(Optional.empty());

        // When & Then
        assertThatThrownBy(() -> attendanceHelper.findAttendanceByIdOrThrow(attendanceId))
                .isInstanceOf(EntityNotFoundException.class);

        verify(attendanceRepository).findById(attendanceId);
    }

    @Test
    @DisplayName("대시보드 출석 정보 조회 - 존재하는 경우")
    void findAttendanceDashboardVoOrNull_WhenExists_ReturnsVo() {
        // Given
        Long missionId = 1L;
        Long userId = 100L;
        AttendanceDashboardVo expectedVo = mock(AttendanceDashboardVo.class);

        when(attendanceRepository.findAttendanceDashboardVo(missionId, userId))
                .thenReturn(expectedVo);

        // When
        AttendanceDashboardVo result = attendanceHelper.findAttendanceDashboardVoOrNull(missionId, userId);

        // Then
        assertThat(result).isEqualTo(expectedVo);
        verify(attendanceRepository).findAttendanceDashboardVo(missionId, userId);
    }

    @Test
    @DisplayName("대시보드 출석 정보 조회 - 존재하지 않는 경우 기본값 반환")
    void findAttendanceDashboardVoOrNull_WhenNotExists_ReturnsDefault() {
        // Given
        Long missionId = 1L;
        Long userId = 100L;

        when(attendanceRepository.findAttendanceDashboardVo(missionId, userId))
                .thenReturn(null);

        // When
        AttendanceDashboardVo result = attendanceHelper.findAttendanceDashboardVoOrNull(missionId, userId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.id()).isNull();
        verify(attendanceRepository).findAttendanceDashboardVo(missionId, userId);
    }

    private Mission createTestMission(int th) {
        Mission mission = mock(Mission.class);
        when(mission.getTh()).thenReturn(th);
        return mission;
    }

    private User createTestUser() {
        User user = mock(User.class);
        return user;
    }
}
