package org.letscareer.letscareer.domain.program.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.letscareer.letscareer.domain.program.entity.SearchCondition;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.program.vo.ProgramForAdminVo;
import org.letscareer.letscareer.domain.program.vo.ProgramForConditionVo;
import org.letscareer.letscareer.domain.program.vo.ProgramReviewNotificationVo;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.letscareer.letscareer.domain.classification.entity.QChallengeClassification.challengeClassification;
import static org.letscareer.letscareer.domain.classification.entity.QLiveClassification.liveClassification;
import static org.letscareer.letscareer.domain.classification.entity.QVodClassification.vodClassification;
import static org.letscareer.letscareer.domain.program.entity.QVWProgram.vWProgram;

@RequiredArgsConstructor
public class ProgramQueryRepositoryImpl implements ProgramQueryRepository {
    private final JPAQueryFactory queryFactory;

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
                        eqIsVisible(),
                        eqProgramType(condition.type()),
                        containDuration(condition.startDate(), condition.endDate()),
                        inProgramClassification(condition.typeList()),
                        inProgramStatus(condition.statusList(), condition.type())
                )
                .groupBy(
                        vWProgram.programId,
                        vWProgram.programType
                )
                .orderBy(
                        orderByProgramStatus(condition.type()),
                        orderByProgramType(),
                        vWProgram.startDate.desc()
                )
                .limit(condition.pageable().getPageSize())
                .offset(condition.pageable().getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(vWProgram.countDistinct())  // 고유한 programId의 개수를 셈
                .from(vWProgram)
                .leftJoin(challengeClassification).on(vWProgram.programType.eq(ProgramType.CHALLENGE).and(vWProgram.programId.eq(challengeClassification.challenge.id)))
                .leftJoin(liveClassification).on(vWProgram.programType.eq(ProgramType.LIVE).and(vWProgram.programId.eq(liveClassification.live.id)))
                .leftJoin(vodClassification).on(vWProgram.programType.eq(ProgramType.VOD).and(vWProgram.programId.eq(vodClassification.vod.id)))
                .where(
                        eqIsVisible(),
                        eqProgramType(condition.type()),
                        containDuration(condition.startDate(), condition.endDate()),
                        inProgramClassification(condition.typeList()),
                        inProgramStatus(condition.statusList(), condition.type())
                )
                .groupBy(
                        vWProgram.programId,
                        vWProgram.programType
                );

        return PageableExecutionUtils.getPage(contents, condition.pageable(), countQuery::fetchCount);
    }

    @Override
    public Page<ProgramForAdminVo> findProgramForAdminVos(SearchCondition condition) {
        List<ProgramForAdminVo> contents = queryFactory
                .select(Projections.constructor(ProgramForAdminVo.class,
                        vWProgram.programId,
                        vWProgram.programType,
                        vWProgram.title,
                        vWProgram.thumbnail,
                        vWProgram.participationCount,
                        vWProgram.zoomLink,
                        vWProgram.zoomPassword,
                        vWProgram.isVisible,
                        vWProgram.startDate,
                        vWProgram.endDate,
                        vWProgram.beginning,
                        vWProgram.deadline,
                        vWProgram.createDate
                ))
                .from(vWProgram)
                .where(
                        eqProgramType(condition.type()),
                        containDuration(condition.startDate(), condition.endDate()),
                        inProgramClassification(condition.typeList()),
                        inProgramStatus(condition.statusList(), condition.type())
                )
                .groupBy(
                        vWProgram.programId,
                        vWProgram.programType
                )
                .orderBy(
                        vWProgram.createDate.desc()
                )
                .limit(condition.pageable().getPageSize())
                .offset(condition.pageable().getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(vWProgram.countDistinct())
                .from(vWProgram)
                .where(
                        eqProgramType(condition.type()),
                        containDuration(condition.startDate(), condition.endDate()),
                        inProgramClassification(condition.typeList()),
                        inProgramStatus(condition.statusList(), condition.type())
                )
                .groupBy(
                        vWProgram.programId,
                        vWProgram.programType
                );

        return PageableExecutionUtils.getPage(contents, condition.pageable(), countQuery::fetchCount);
    }

    @Override
    public List<ProgramReviewNotificationVo> findProgramReviewNotificationVos() {
        return queryFactory
                .select(Projections.constructor(ProgramReviewNotificationVo.class,
                        vWProgram.programId,
                        vWProgram.programType))
                .from(vWProgram)
                .where(
                    isChallengeOrLive(),
                    isDayAfterEndDate()
                )
                .fetch();
    }

    @Override
    public List<CurationItemVo> findCurationImminentProgramVos() {
        return queryFactory
                .select(Projections.constructor(CurationItemVo.class,
                        Expressions.constant(0L),
                        vWProgram.curationItemProgramType,
                        vWProgram.programId,
                        Expressions.nullExpression(LocalDateTime.class),
                        vWProgram.startDate,
                        vWProgram.endDate,
                        vWProgram.deadline,
                        Expressions.nullExpression(BlogType.class),
                        Expressions.nullExpression(ReportType.class),
                        Expressions.nullExpression(String.class),
                        vWProgram.title,
                        Expressions.nullExpression(String.class),
                        vWProgram.thumbnail))
                .from(vWProgram)
                .where(
                        eqIsVisible(),
                        isImminent()
                )
                .orderBy(
                        vWProgram.deadline.asc()
                )
                .fetch();
    }

    private BooleanExpression isImminent() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusFiveDay = LocalDateTime.now().plusDays(5);
        return vWProgram.deadline.goe(now).and(vWProgram.deadline.loe(nowPlusFiveDay));
    }

    private BooleanExpression isDayAfterEndDate() {
        LocalDate nowMinusOneDay = LocalDate.now().minusDays(1);
        return Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, '%Y-%m-%d')", vWProgram.endDate).eq(nowMinusOneDay);
    }

    private BooleanExpression isChallengeOrLive() {
        return vWProgram.programType.eq(ProgramType.CHALLENGE).or(vWProgram.programType.eq(ProgramType.LIVE));
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

    private BooleanBuilder inProgramStatus(List<ProgramStatusType> statusList, List<ProgramType> type) {
        if (statusList == null || statusList.isEmpty()) return null;
        LocalDateTime now = LocalDateTime.now();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        statusList.forEach(status -> booleanBuilder.or(addBooleanExpression(status, now, type)));
        return booleanBuilder;
    }

    public OrderSpecifier<?> orderByProgramType() {
        return new CaseBuilder()
                .when(vWProgram.programType.eq(ProgramType.CHALLENGE)).then(0)
                .when(vWProgram.programType.eq(ProgramType.LIVE)).then(1)
                .when(vWProgram.programType.eq(ProgramType.VOD)).then(2)
                .otherwise(3)
                .asc();
    }

    private OrderSpecifier<?> orderByProgramStatus(List<ProgramType> type) {
        LocalDateTime now = LocalDateTime.now();
        // PROCEEDING 상태인 프로그램
        BooleanExpression proceedingStatus = orderProceedingStatus(now, type);
        // PREV 상태인 프로그램
        BooleanExpression prevStatus = programPrevStatus(now).and(proceedingStatus.not());
        // POST 상태인 프로그램
        BooleanExpression postStatus = programPostStatus(now).and(proceedingStatus.not()).and(prevStatus.not());
        // PROCEEDING -> PREV -> POST 순으로 정렬
        return new CaseBuilder()
                .when(proceedingStatus).then(0)
                .when(prevStatus).then(1)
                .when(postStatus).then(2)
                .otherwise(0)
                .asc();
    }

    private BooleanExpression addBooleanExpression(ProgramStatusType programStatusType, LocalDateTime now, List<ProgramType> type) {
        if (ProgramStatusType.PREV.equals(programStatusType))
            return programPrevStatus(now);
        else if (ProgramStatusType.PROCEEDING.equals(programStatusType))
            return programProceedingStatus(now);
        else if (ProgramStatusType.POST.equals(programStatusType))
            return programPostStatus(now);
        return null;
    }

    private BooleanExpression orderProceedingStatus(LocalDateTime now, List<ProgramType> type) {
        if (type == null || type.isEmpty())
            return vWProgram.beginning.loe(now).and(vWProgram.deadline.goe(now));
        if (type.contains(ProgramType.VOD))
            return vWProgram.programType.eq(ProgramType.VOD).or(vWProgram.beginning.loe(now).and(vWProgram.deadline.goe(now)));
        return vWProgram.beginning.loe(now).and(vWProgram.deadline.goe(now));
    }

    private BooleanExpression programProceedingStatus(LocalDateTime now) {
        return vWProgram.programType.eq(ProgramType.VOD).or(vWProgram.beginning.loe(now).and(vWProgram.deadline.goe(now)));
    }

    private BooleanExpression programPrevStatus(LocalDateTime now) {
        return vWProgram.beginning.gt(now);
    }

    private BooleanExpression programPostStatus(LocalDateTime now) {
        return vWProgram.deadline.lt(now);
    }

    private BooleanExpression eqIsVisible() {
        return vWProgram.isVisible.eq(true);
    }
}
