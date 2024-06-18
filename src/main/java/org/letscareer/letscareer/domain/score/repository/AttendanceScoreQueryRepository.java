package org.letscareer.letscareer.domain.score.repository;

import org.letscareer.letscareer.domain.score.entity.AttendanceScore;

import java.util.Optional;

public interface AttendanceScoreQueryRepository {
    Optional<AttendanceScore> findAttendanceScoreByChallengeIdAndApplicationId(Long challengeId, Long applicationId);

    Optional<Integer> getSumOfAttendanceScoreByChallengeIdAndUserId(Long challengeId, Long userId);

    Optional<AttendanceScore> findAttendanceScoreByMissionId(Long missionId, Long applicationId);
}
