package org.letscareer.letscareer.domain.mission.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.mission.vo.DailyMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionScheduleVo;
import org.letscareer.letscareer.domain.mission.vo.MyDailyMissionVo;
import org.letscareer.letscareer.domain.contents.type.ContentsType;
import org.letscareer.letscareer.domain.contents.vo.ContentsMissionVo;
import org.letscareer.letscareer.domain.mission.vo.MissionForChallengeVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.attendance.entity.QAttendance.attendance;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.contents.entity.QContents.contents;
import static org.letscareer.letscareer.domain.mission.entity.QMission.mission;
import static org.letscareer.letscareer.domain.missioncontents.entity.QMissionContents.missionContents;
import static org.letscareer.letscareer.domain.missiontemplate.entity.QMissionTemplate.missionTemplate;
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
                        missionTemplate.missionTag,
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
                .leftJoin(mission.missionTemplate, missionTemplate)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
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
                                mission.type,
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
    public List<MissionScheduleVo> findMissionScheduleVosByChallengeIdAndUserId(Long challengeId, Long userId) {
        return queryFactory
                .select(Projections.constructor(MissionScheduleVo.class,
                        mission.id,
                        mission.th,
                        mission.startDate,
                        mission.endDate,
                        mission.missionStatusType,
                        attendance))
                .from(mission)
                .leftJoin(mission.attendanceList, attendance)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(mission.th.asc())
                .fetch();
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression inProgress() {
        LocalDateTime now = LocalDateTime.now();
        return mission.startDate.before(now).and(mission.endDate.after(now));
    }
}
