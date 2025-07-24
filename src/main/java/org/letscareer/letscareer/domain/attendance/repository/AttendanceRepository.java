package org.letscareer.letscareer.domain.attendance.repository;

import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceQueryRepository {
    Optional<Attendance> findAttendanceByMissionIdAndUserId(Long missionId, Long userId);

    boolean existsByMissionChallengeIdAndMissionThAndUserIdAndResult(
            Long challengeId, Integer th, Long userId, AttendanceResult result);
}
