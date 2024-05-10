package org.letscareer.letscareer.domain.attendance.repository;

import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;

import java.util.List;

public interface AttendanceQueryRepository {
    List<AttendanceAdminVo> findAllAttendanceByChallengeId(Long challengeId);
}
