package org.letscareer.letscareer.domain.mission.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.util.List;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.contents.entity.QContents.contents;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;
import static org.letscareer.letscareer.domain.missioncontents.entity.QMissionContents.missionContents;
import static org.letscareer.letscareer.domain.score.entity.QMissionScore.missionScore;

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
                        missionScore.successScore,
                        missionScore.lateScore,
                        mission.startDate,
                        mission.endDate
                ))
                .from(mission)
                .leftJoin(mission.challenge, challenge)
                .leftJoin(mission.missionScore, missionScore)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
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

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }
}
