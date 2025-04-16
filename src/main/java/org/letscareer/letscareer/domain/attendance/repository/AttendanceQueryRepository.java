package org.letscareer.letscareer.domain.attendance.repository;

import org.letscareer.letscareer.domain.attendance.vo.*;

import java.util.List;

public interface AttendanceQueryRepository {
    List<AttendanceAdminVo> findAllAttendanceByChallengeId(Long challengeId);
    List<MissionScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId);
    List<MissionAttendanceWithOptionsVo> findMissionAttendanceVo(Long challengeId, Long missionId);
    AttendanceDashboardVo findAttendanceDashboardVo(Long missionId, Long userId);
    List<MissionReviewAdminVo> findAllMissionReviewAdminVos();
}
