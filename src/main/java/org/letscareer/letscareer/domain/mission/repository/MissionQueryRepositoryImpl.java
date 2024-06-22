package org.letscareer.letscareer.domain.mission.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.type.AttendanceStatus;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.type.MissionQueryType;
import org.letscareer.letscareer.domain.mission.vo.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.attendance.entity.QAttendance.attendance;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.contents.entity.QContents.contents;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;
import static org.letscareer.letscareer.domain.missioncontents.entity.QMissionContents.missionContents;
import static org.letscareer.letscareer.domain.missiontemplate.entity.QMissionTemplate.missionTemplate;
import static org.letscareer.letscareer.domain.score.entity.QMissionScore.missionScore;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionQueryRepositoryImpl implements MissionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(MissionForChallengeVo.class,
                        mission.id,
                        mission.title,
                        mission.th,
                        missionTemplate.missionTag,
                        mission.missionStatusType,
                        getAttendanceCount(),
                        getLateAttendanceCount(),
                        missionScore.successScore,
                        missionScore.lateScore,
                        missionTemplate.id,
                        mission.startDate,
                        mission.endDate
                ))
                .from(mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(mission.missionScore, missionScore)
                .leftJoin(mission.missionTemplate, missionTemplate)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<Mission> findMissionsByChallengeId(Long challengeId) {
        return queryFactory
                .select(mission)
                .from(challenge)
                .leftJoin(challenge.missionList, mission)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetch();
    }

    @Override
    public Optional<DailyMissionVo> findDailyMissionVoByChallengeId(Long challengeId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(DailyMissionVo.class,
                                mission.id,
                                mission.th,
                                mission.title,
                                mission.startDate,
                                mission.endDate,
                                missionTemplate.missionTag,
                                missionTemplate.description))
                        .from(mission)
                        .leftJoin(mission.missionTemplate, missionTemplate)
                        .where(
                                eqChallengeId(challengeId),
                                inProgress()
                        )
                        .fetchFirst()
        );
    }

    @Override
    public Optional<MyDailyMissionVo> findMyDailyMissionVoByChallengeId(Long challengeId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(MyDailyMissionVo.class,
                                mission,
                                missionTemplate.missionTag,
                                missionTemplate.description,
                                missionTemplate.guide,
                                missionTemplate.templateLink))
                        .from(mission)
                        .leftJoin(mission.missionTemplate, missionTemplate)
                        .where(
                                eqChallengeId(challengeId),
                                inProgress()
                        )
                        .fetchFirst()
        );
    }

    public List<ContentsMissionVo> findMissionContentsVos(Long missionIdPath, ContentsType contentsType) {
        return queryFactory
                .select(Projections.constructor(ContentsMissionVo.class,
                        contents.id,
                        contents.title,
                        contents.link
                ))
                .from(missionContents)
                .leftJoin(missionContents.contents, contents)
                .where(
                        missionContents.mission.id.eq(missionIdPath),
                        missionContents.contentsType.eq(contentsType)
                )
                .fetch();
    }

    @Override
    public List<MissionScheduleVo> findMissionScheduleVosByChallengeId(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(MissionScheduleVo.class,
                        mission.id,
                        mission.title,
                        mission.th,
                        mission.startDate,
                        mission.endDate,
                        mission.missionStatusType))
                .from(mission)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<MySubmittedMissionVo> findMySubmittedMissionVosByChallengeIdAndUserId(Long challengeId, Long userId) {
        return queryFactory
                .select(Projections.constructor(MySubmittedMissionVo.class,
                        mission.id,
                        mission.th,
                        mission.title,
                        attendance.link,
                        attendance.status,
                        attendance.result))
                .from(mission)
                .leftJoin(mission.attendanceList, attendance)
                .where(
                        eqChallengeId(challengeId),
                        isSubmitted(userId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<MyMissionVo> findMyMissionVosByChallengeIdAndUserId(Long challengeId, MissionQueryType queryType, Long userId) {
        return queryFactory
                .select(Projections.constructor(MyMissionVo.class,
                        mission.id,
                        mission.th,
                        mission.title))
                .from(mission)
                .leftJoin(mission.attendanceList, attendance)
                .on(isSubmitted(userId))
                .where(
                        eqChallengeId(challengeId),
                        eqQueryType(queryType),
                        attendance.isNull()
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public Optional<Integer> finsSumOfMissionScoresByChallengeId(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(missionScore.successScore.sum())
                .from(mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(mission.missionScore, missionScore)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<Integer> findApplicationScoreByMissionId(Long missionId, Long applicationId) {
        return Optional.ofNullable(queryFactory
                .select(getAttendanceScore())
                .from(mission)
                .leftJoin(mission.missionScore, missionScore)
                .leftJoin(mission.attendanceList, attendance)
                .leftJoin(attendance.user.applicationList, application)
                .where(
                        eqMissionId(missionId),
                        eqApplicationId(applicationId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<Integer> findAttendanceScoreByMissionIdAndUserId(Long missionId, Long userId) {
        return Optional.ofNullable(queryFactory
                .select(getAttendanceScore())
                .from(mission)
                .leftJoin(mission.missionScore, missionScore)
                .leftJoin(mission.attendanceList, attendance)
                .leftJoin(attendance.user, user)
                .where(
                        eqMissionId(missionId),
                        eqUserId(userId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<MissionDetailVo> findMissionDetailVo(Long missionId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(MissionDetailVo.class,
                        mission.id,
                        mission.title,
                        mission.missionStatusType,
                        getAttendanceCount(),
                        getLateAttendanceCount(),
                        mission.startDate,
                        mission.endDate
                ))
                .from(mission)
                .where(
                        eqMissionId(missionId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<MyDailyMissionVo> findMyDailyMissionVoByMissionId(Long missionId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(MyDailyMissionVo.class,
                                mission,
                                missionTemplate.missionTag,
                                missionTemplate.description,
                                missionTemplate.guide,
                                missionTemplate.templateLink))
                        .from(mission)
                        .leftJoin(mission.missionTemplate, missionTemplate)
                        .where(
                                eqMissionId(missionId)
                        )
                        .fetchFirst()
        );
    }

    private Expression<Long> getAttendanceCount() {
        return JPAExpressions.select(attendance.status.count())
                .from(attendance)
                .where(
                        attendance.mission.eq(mission)
                                .and(attendance.status.notIn(AttendanceStatus.ABSENT))
                                .and(attendance.result.notIn(AttendanceResult.WRONG))
                );
    }

    private Expression<Long> getLateAttendanceCount() {
        return JPAExpressions.select(attendance.status.count())
                .from(attendance)
                .where(
                        attendance.mission.eq(mission)
                                .and(attendance.status.in(AttendanceStatus.LATE))
                                .and(attendance.result.notIn(AttendanceResult.WRONG))
                );
    }

    private Expression<Integer> getAttendanceScore() {
        return new CaseBuilder()
                .when(attendance.isNull())
                .then(0)
                .when(attendance.status.eq(AttendanceStatus.PRESENT).and(attendance.result.eq(AttendanceResult.PASS)))
                .then(missionScore.successScore)
                .when(attendance.status.eq(AttendanceStatus.LATE).and(attendance.result.eq(AttendanceResult.PASS)))
                .then(missionScore.lateScore)
                .when(attendance.status.eq(AttendanceStatus.UPDATED).and(attendance.result.eq(AttendanceResult.PASS)))
                .then(missionScore.lateScore)
                .otherwise(0);
    }

    private Expression<Integer> getSumOfAttendanceScore() {
        return new CaseBuilder()
                .when(attendance.isNull())
                .then(0)
                .when(attendance.status.eq(AttendanceStatus.PRESENT).and(attendance.result.eq(AttendanceResult.PASS)))
                .then(missionScore.successScore)
                .when(attendance.status.eq(AttendanceStatus.LATE).and(attendance.result.eq(AttendanceResult.PASS)))
                .then(missionScore.lateScore)
                .when(attendance.status.eq(AttendanceStatus.UPDATED).and(attendance.result.eq(AttendanceResult.PASS)))
                .then(missionScore.lateScore)
                .otherwise(0)
                .sum();
    }

    private BooleanExpression eqMissionId(Long missionId) {
        return missionId != null ? mission.id.eq(missionId) : null;
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? application.id.eq(applicationId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanBuilder eqQueryType(MissionQueryType queryType) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (queryType != null) {
            LocalDateTime now = LocalDateTime.now();
            switch (queryType) {
                case REMAINING -> booleanBuilder.and(mission.startDate.after(now));
                case ABSENT -> booleanBuilder.and(mission.endDate.before(now));
            }
        }
        return booleanBuilder;
    }

    private BooleanExpression isSubmitted(Long userId) {
        return userId != null ? attendance.user.id.eq(userId).and(attendance.in(mission.attendanceList)) : null;
    }

    private BooleanExpression inProgress() {
        LocalDateTime now = LocalDateTime.now();
        return mission.startDate.before(now).and(mission.endDate.after(now));
    }
}
