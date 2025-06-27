package org.letscareer.letscareer.domain.attendance.repository;

import org.letscareer.letscareer.domain.attendance.vo.*;

import java.util.List;
import java.util.Optional;

public interface AttendanceQueryRepository {
    List<AttendanceAdminVo> findAllAttendanceByChallengeId(Long challengeId);
    List<MissionScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId);
    List<MissionAttendanceWithOptionsVo> findMissionAttendanceVos(Long challengeId, Long missionId);
    List<FeedbackMissionAttendanceVo> findFeedbackMissionAttendanceVos(Long mentorId, Long challengeId, Long missionId, Long challengeOptionId);
    Optional<FeedbackMissionAttendanceDetailVo> findFeedbackMissionAttendanceDetailVoByAttendanceId(Long attendanceId);
    AttendanceDashboardVo findAttendanceDashboardVo(Long missionId, Long userId);
    AttendanceFeedbackVo findAttendanceFeedbackVo(Long missionId, Long userId);
    List<MissionReviewAdminVo> findAllMissionReviewAdminVos();
}
