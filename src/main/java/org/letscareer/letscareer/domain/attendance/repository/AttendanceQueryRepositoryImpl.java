package org.letscareer.letscareer.domain.attendance.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.attendance.type.AttendanceResult;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceAdminVo;
import org.letscareer.letscareer.domain.attendance.vo.AttendanceDashboardVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionScoreVo;
import org.letscareer.letscareer.domain.attendance.vo.MissionAttendanceVo;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.attendance.entity.QAttendance.attendance;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;
import static org.letscareer.letscareer.domain.user.repository.UserQueryQueryRepositoryImpl.activeEmail;

@RequiredArgsConstructor
public class AttendanceQueryRepositoryImpl implements AttendanceQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AttendanceAdminVo> findAllAttendanceByChallengeId(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(AttendanceAdminVo.class,
                        attendance.id,
                        attendance.user.name,
                        activeEmail(attendance.user),
                        attendance.user.accountType,
                        attendance.user.accountNum,
                        attendance.user.accountOwner,
                        attendance.status,
                        attendance.link,
                        attendance.result,
                        attendance.comments))
                .from(attendance)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(
                        resultOrder().asc(),
                        attendance.id.desc()
                )
                .fetch();
    }

    @Override
    public List<MissionScoreVo> findAttendanceScoreVos(Long applicationId, Long challengeId) {
        return queryFactory
                .select(Projections.constructor(MissionScoreVo.class,
                        mission.id,
                        mission.th
                ))
                .from(mission)
                .leftJoin(mission.challenge, challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<MissionAttendanceVo> findMissionAttendanceVo(Long challengeId, Long missionId) {
        return queryFactory
                .select(Projections.constructor(MissionAttendanceVo.class,
                        attendance.id,
                        attendance.user.name,
                        activeEmail(attendance.user),
                        attendance.status,
                        attendance.link,
                        attendance.result,
                        attendance.comments,
                        attendance.createDate,
                        attendance.lastModifiedDate
                ))
                .from(attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(attendance.mission.challenge, challenge)
                .where(
                        eqChallengeId(challengeId),
                        eqMissionId(missionId)
                )
                .orderBy(
                        resultOrder().asc(),
                        attendance.id.desc()
                )
                .fetch();
    }

    @Override
    public AttendanceDashboardVo findAttendanceDashboardVo(Long missionId, Long userId) {
        return queryFactory
                .select(Projections.constructor(AttendanceDashboardVo.class,
                        attendance))
                .from(attendance)
                .leftJoin(attendance.mission, mission)
                .leftJoin(attendance.user, user)
                .where(
                        eqMissionId(missionId),
                        eqUserId(userId)
                )
                .fetchFirst();
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? attendance.user.id.eq(userId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqChallengeApplicationId(Long applicationId) {
        return applicationId != null ? challengeApplication._super.id.eq(applicationId) : null;
    }

    private BooleanExpression eqMissionId(Long missionId) {
        return missionId != null ? mission.id.eq(missionId) : null;
    }

    private NumberExpression<Integer> resultOrder() {
        return new CaseBuilder()
                .when(attendance.result.eq(AttendanceResult.WAITING)).then(0)
                .when(attendance.result.eq(AttendanceResult.WRONG)).then(1)
                .otherwise(2);
    }
}
