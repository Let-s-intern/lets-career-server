package org.letscareer.letscareer.domain.score.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.entity.Attendance;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;
import org.letscareer.letscareer.domain.score.entity.MissionScore;
import org.letscareer.letscareer.domain.score.repository.AttendanceScoreRepository;
import org.letscareer.letscareer.global.error.exception.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static org.letscareer.letscareer.domain.score.error.ScoreErrorCode.ATTENDANCE_SCORE_NOT_FOUND;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class AttendanceScoreHelper {
    private final AttendanceScoreRepository attendanceScoreRepository;

    public AttendanceScore findAttendanceScoreByChallengeIdAndApplicationIdOrThrow(Long challengeId, Long applicationId) {
        return attendanceScoreRepository.findAttendanceScoreByChallengeIdAndApplicationId(challengeId, applicationId)
                .orElseThrow(() -> new EntityNotFoundException(ATTENDANCE_SCORE_NOT_FOUND));
    }

    public AttendanceScore createAttendanceScoreAndSave(AttendanceStatus status, MissionScore missionScore, Attendance attendance) {
        int score = 0;
        switch (status) {
            case PRESENT -> score = missionScore.getSuccessScore();
            case LATE -> score = missionScore.getLateScore();
        }
        AttendanceScore newAttendanceScore = AttendanceScore.creatAttendanceScore(score, attendance);
        return attendanceScoreRepository.save(newAttendanceScore);
    }

    public Integer getSumOfAttendanceScoreByChallengeIdAndUserId(Long challengeId, Long userId) {
        return attendanceScoreRepository.getSumOfAttendanceScoreByChallengeIdAndUserId(challengeId, userId).orElse(0);
    }
}
