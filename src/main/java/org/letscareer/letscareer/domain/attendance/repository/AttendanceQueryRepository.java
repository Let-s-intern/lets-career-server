package org.letscareer.letscareer.domain.attendance.repository;

import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceScoreVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;

import java.util.List;

public interface AttendanceQueryRepository {
    List<AttendanceAdminVo> findAllAttendanceByChallengeId(Long challengeId);
    List<AttendanceScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId);
    List<MissionAttendanceVo> findMissionAttendanceVo(Long challengeId, Long missionId);
    AttendanceDashboardVo findAttendanceDashboardVo(Long missionId, Long userId);
}
