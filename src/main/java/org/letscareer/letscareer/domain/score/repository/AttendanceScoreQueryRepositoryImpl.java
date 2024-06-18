package org.letscareer.letscareer.domain.score.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;

import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.attendance.entity.QAttendance.attendance;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;
import static org.letscareer.letscareer.domain.score.entity.QAttendanceScore.attendanceScore;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceScoreQueryRepositoryImpl implements AttendanceScoreQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<AttendanceScore> findAttendanceScoreByChallengeIdAndApplicationId(Long challengeId, Long applicationId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(attendanceScore)
                .leftJoin(attendanceScore.attendance, attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(challenge.applicationList, challengeApplication)
                .leftJoin(challengeApplication._super, application)
                .where(
                        eqChallengeId(challengeId),
                        eqApplicationId(applicationId)
                )
                .fetchFirst()
        );
    }

    @Override
    public Optional<Integer> getSumOfAttendanceScoreByChallengeIdAndUserId(Long challengeId, Long userId) {
        return Optional.ofNullable(queryFactory
                .select(attendanceScore.score.sum())
                .from(attendanceScore)
                .leftJoin(attendanceScore.attendance, attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(attendance.user, user)
                .where(
                        eqChallengeId(challengeId),
                        eqUserId(userId)
                )
                .fetchFirst());
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? application.id.eq(applicationId) : null;
    }
}
