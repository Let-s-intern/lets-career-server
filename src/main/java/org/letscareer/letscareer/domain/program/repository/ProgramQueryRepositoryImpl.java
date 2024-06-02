package org.letscareer.letscareer.domain.program.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.entity.VWProgram;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.AdminProgramVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.classification.entity.QChallengeClassification.challengeClassification;
import static org.letscareer.letscareer.domain.classification.entity.QLiveClassification.liveClassification;
import static org.letscareer.letscareer.domain.classification.entity.QVodClassification.vodClassification;
import static org.letscareer.letscareer.domain.program.entity.QVWProgram.vWProgram;

@RequiredArgsConstructor
public class ProgramQueryRepositoryImpl implements ProgramQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public Page<AdminProgramVo> findAdminProgramVos(Pageable pageable) {
        List<AdminProgramVo> contents = queryFactory
                .select(Projections.constructor(AdminProgramVo.class,
                        vWProgram.programId,
                        vWProgram.programType,
                        vWProgram.title,
                        vWProgram.currentCount,
                        vWProgram.participationCount,
                        vWProgram.startDate,
                        vWProgram.endDate,
                        vWProgram.deadline,
                        vWProgram.isVisible,
                        vWProgram.zoomLink,
                        vWProgram.zoomPassword
                ))
                .from(vWProgram)
                .orderBy(vWProgram.createDate.desc())
                .fetch();

        JPAQuery<VWProgram> countQuery = queryFactory
                .selectFrom(vWProgram)
                .orderBy(vWProgram.createDate.desc());

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<ProgramForConditionVo> findProgramForConditionVos(SearchCondition condition) {
        List<ProgramForConditionVo> contents = queryFactory
                .select(Projections.constructor(ProgramForConditionVo.class,
                        vWProgram.programId,
                        vWProgram.programType,
                        vWProgram.title,
                        vWProgram.thumbnail,
                        vWProgram.shortDesc,
                        vWProgram.startDate,
                        vWProgram.endDate,
                        vWProgram.beginning,
                        vWProgram.deadline
                ))
                .from(vWProgram)
                .leftJoin(challengeClassification).on(vWProgram.programType.eq(ProgramType.CHALLENGE).and(vWProgram.programId.eq(challengeClassification.challenge.id)))
                .leftJoin(liveClassification).on(vWProgram.programType.eq(ProgramType.LIVE).and(vWProgram.programId.eq(liveClassification.live.id)))
                .leftJoin(vodClassification).on(vWProgram.programType.eq(ProgramType.VOD).and(vWProgram.programId.eq(vodClassification.vod.id)))
                .where(
                        eqProgramType(condition.type()),
                        containDuration(condition.startDate(), condition.endDate()),
                        inProgramClassification(condition.typeList()),
                        inProgramStatus(condition.statusList())
                )
                .orderBy(vWProgram.startDate.desc())
                .limit(condition.pageable().getPageSize())
                .offset(condition.pageable().getOffset())
                .fetch();

        JPAQuery<VWProgram> countQuery = queryFactory
                .selectFrom(vWProgram)
                .where(
                        eqProgramType(condition.type()),
                        containDuration(condition.startDate(), condition.endDate()),
                        inProgramClassification(condition.typeList()),
                        inProgramStatus(condition.statusList())
                )
                .orderBy(vWProgram.startDate.desc());

        return PageableExecutionUtils.getPage(contents, condition.pageable(), countQuery::fetchCount);
    }

    private BooleanExpression eqProgramType(List<ProgramType> programType) {
        return programType != null ? vWProgram.programType.in(programType) : null;
    }

    private BooleanExpression containDuration(LocalDateTime startDate, LocalDateTime endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) return null;
        return vWProgram.startDate.between(startDate, endDate);
    }

    private BooleanExpression inProgramClassification(List<ProgramClassification> typeList) {
        if (typeList == null || typeList.isEmpty()) return null;

        BooleanExpression challengeCondition = vWProgram.programType.eq(ProgramType.CHALLENGE)
                .and(challengeClassification.programClassification.in(typeList));

        BooleanExpression liveCondition = vWProgram.programType.eq(ProgramType.LIVE)
                .and(liveClassification.programClassification.in(typeList));

        BooleanExpression vodCondition = vWProgram.programType.eq(ProgramType.VOD)
                .and(vodClassification.programClassification.in(typeList));

        return challengeCondition.or(liveCondition).or(vodCondition);
    }

    private BooleanBuilder inProgramStatus(List<ProgramStatusType> statusList) {
        if (statusList == null || statusList.isEmpty()) return null;
        LocalDateTime now = LocalDateTime.now();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        statusList.forEach(status -> booleanBuilder.or(addBooleanExpression(status, now)));
        return booleanBuilder;
    }

    private BooleanExpression addBooleanExpression(ProgramStatusType programStatusType, LocalDateTime now) {
        if (ProgramStatusType.PREV.equals(programStatusType))
            return programPrevStatus(now);
        else if (ProgramStatusType.PROCEEDING.equals(programStatusType))
            return programProceedingStatus(now);
        else if (ProgramStatusType.POST.equals(programStatusType))
            return programPostStatus(now);
        return null;
    }

    private BooleanExpression programPrevStatus(LocalDateTime now) {
        return challenge.startDate.lt(now);
    }

    private BooleanExpression programProceedingStatus(LocalDateTime now) {
        return challenge.startDate.loe(now).and(challenge.endDate.goe(now));
    }

    private BooleanExpression programPostStatus(LocalDateTime now) {
        return challenge.endDate.lt(now);
    }
}
