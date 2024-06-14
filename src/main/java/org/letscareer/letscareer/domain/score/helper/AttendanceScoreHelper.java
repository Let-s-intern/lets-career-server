package org.letscareer.letscareer.domain.score.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;
import org.letscareer.letscareer.domain.score.repository.AttendanceScoreRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.score.error.ScoreErrorException.ATTENDANCE_SCORE_NOT_FOUND;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class AttendanceScoreHelper {
    private final AttendanceScoreRepository attendanceScoreRepository;

    public AttendanceScore findAttendanceScoreByChallengeIdAndApplicationIdOrThrow(Long challengeId, Long applicationId) {
        return attendanceScoreRepository.findAttendanceScoreByChallengeIdAndApplicationId(challengeId, applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ATTENDANCE_SCORE_NOT_FOUND));
    }
}
