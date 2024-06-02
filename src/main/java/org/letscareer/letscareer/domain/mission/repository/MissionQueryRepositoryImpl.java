package org.letscareer.letscareer.domain.mission.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MissionQueryRepositoryImpl implements MissionQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MissionForChallengeVo> findMissionForChallengeVos(Long challengeId) {
        return queryFactory
                .select(Projections.constructor(MissionForChallengeVo.class,
                        mission.id,
                        mission.th,
                        mission.missionStatusType,
                        mission.attendanceCount,
                        mission.lateAttendanceCount,
                        mission.startDate,
                        mission.endDate
                ))
                .from(mission)
                .leftJoin(mission.challenge, challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }
}
