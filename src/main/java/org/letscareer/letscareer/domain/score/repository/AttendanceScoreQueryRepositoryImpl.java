package org.letscareer.letscareer.domain.score.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.score.entity.AttendanceScore;

import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
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
                .leftJoin(attendance.user, user)
                .leftJoin(user.applicationList, application)
                .leftJoin(attendance.mission, mission)
                .leftJoin(mission.challenge, challenge)
                .where(
                        eqChallengeId(challengeId),
                        eqApplicationId(applicationId)
                )
                .fetchFirst()
        );
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? application.id.eq(applicationId) : null;
    }
}
