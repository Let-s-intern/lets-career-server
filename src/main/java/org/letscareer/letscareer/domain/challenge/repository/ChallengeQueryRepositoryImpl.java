package org.letscareer.letscareer.domain.challenge.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.challenge.vo.*;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.curation.type.CurationItemProgramType;
import org.letscareer.letscareer.domain.curation.vo.CurationItemVo;
import org.letscareer.letscareer.domain.price.type.ChallengeParticipationType;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.classification.entity.QChallengeClassification.challengeClassification;
import static org.letscareer.letscareer.domain.price.entity.QChallengePrice.challengePrice;

@RequiredArgsConstructor
public class ChallengeQueryRepositoryImpl implements ChallengeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChallengeDetailVo> findChallengeDetailById(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeDetailVo.class,
                        challenge.id,
                        challenge.title,
                        challenge.shortDesc,
                        challenge.description,
                        challenge.criticalNotice,
                        challenge.participationCount,
                        challenge.thumbnail,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.beginning,
                        challenge.deadline,
                        challenge.chatLink,
                        challenge.chatPassword,
                        challenge.challengeType
                ))
                .from(challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    @Override
    public Page<ChallengeProfileVo> findChallengeProfiles(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        List<ChallengeProfileVo> contents = queryFactory
                .select(Projections.constructor(ChallengeProfileVo.class,
                        challenge.id,
                        challenge.title,
                        challenge.shortDesc,
                        challenge.thumbnail,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.beginning,
                        challenge.deadline,
                        challenge.createDate
                ))
                .from(challenge)
                .leftJoin(challenge.classificationList, challengeClassification)
                .orderBy(challenge.id.desc())
                .where(
                        inChallengeClassification(typeList),
                        inChallengeStatus(statusList)
                )
                .groupBy(challenge.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(challenge.id.countDistinct())
                .from(challenge)
                .leftJoin(challenge.classificationList, challengeClassification)
                .where(
                        inChallengeClassification(typeList),
                        inChallengeStatus(statusList)
                )
                .groupBy(challenge.id);

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public List<ChallengeSimpleProfileVo> findActiveChallengeProfiles(ChallengeType challengeType) {
        return queryFactory
                .select(Projections.constructor(ChallengeSimpleProfileVo.class,
                        challenge.id,
                        challenge.title,
                        challenge.beginning,
                        challenge.deadline,
                        challenge.startDate,
                        challenge.endDate))
                .from(challenge)
                .where(
                        eqChallengeType(challengeType),
                        eqIsVisible(true),
                        challengeRecruiting(LocalDateTime.now())
                )
                .orderBy(challenge.id.asc())
                .fetch();
    }

    @Override
    public Optional<ChallengeTitleVo> findChallengeTitleVo(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeTitleVo.class,
                        challenge.title
                ))
                .from(challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<ChallengeThumbnailVo> findChallengeThumbnailVo(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeThumbnailVo.class,
                        challenge.thumbnail
                ))
                .from(challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<ChallengeContentVo> findChallengeContentVo(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeContentVo.class,
                        challenge.description
                ))
                .from(challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<ChallengeApplicationFormVo> findChallengeApplicationFormVo(Long challengeId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ChallengeApplicationFormVo.class,
                        challenge.criticalNotice,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.beginning,
                        challenge.deadline
                ))
                .from(challenge)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne()
        );
    }

    @Override
    public List<Long> findAllRemindNotificationChallengeId() {
        return queryFactory
                .select(challenge.id)
                .from(challenge)
                .leftJoin(challenge.priceList, challengePrice)
                .where(
                        eqChallengeParticipationType(ChallengeParticipationType.LIVE),
                        isDayBeforeStartDate(1)
                )
                .fetch();
    }

    @Override
    public List<Long> findAllEndNotificationChallengeId() {
        return queryFactory
                .select(challenge.id)
                .from(challenge)
                .leftJoin(challenge.priceList, challengePrice)
                .where(
                        eqChallengeParticipationType(ChallengeParticipationType.LIVE),
                        isDayAfterEndDate(2)
                )
                .fetch();
    }

    @Override
    public List<Long> findAllOTRemindNotificationChallengeId() {
        return queryFactory
                .select(challenge.id)
                .from(challenge)
                .leftJoin(challenge.priceList, challengePrice)
                .where(
                        eqChallengeParticipationType(ChallengeParticipationType.LIVE),
                        isHourBeforeStartDate(1)
                )
                .fetch();
    }

    @Override
    public ChallengeRecommendVo findChallengeRecommendVoByChallengeType(ChallengeType challengeType) {
        return queryFactory
                .select(Projections.constructor(ChallengeRecommendVo.class,
                        challenge.id,
                        Expressions.constant(ProgramType.CHALLENGE),
                        challenge.challengeType,
                        challenge.title,
                        challenge.thumbnail,
                        challenge.shortDesc,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.beginning,
                        challenge.deadline))
                .from(challenge)
                .where(
                        eqIsVisible(true),
                        eqChallengeType(challengeType)
                )
                .orderBy(
                        orderByRecommendCondition().toArray(new OrderSpecifier[0])
                )
                .fetchFirst();
    }

    @Override
    public CurationItemVo findCurationItemVoByKeyword(String keyword) {
        return queryFactory
                .select(Projections.constructor(CurationItemVo.class,
                        Expressions.constant(0L),
                        Expressions.constant(CurationItemProgramType.CHALLENGE),
                        challenge.id,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.deadline,
                        Expressions.nullExpression(ReportType.class),
                        challenge.title,
                        Expressions.nullExpression(String.class),
                        challenge.thumbnail))
                .from(challenge)
                .where(
                        eqIsVisible(true),
                        challengeRecruiting(LocalDateTime.now()),
                        titleContains(keyword)
                )
                .orderBy(
                        challenge.deadline.asc()
                )
                .fetchFirst();
    }

    private BooleanExpression titleContains(String keyword) {
        return keyword != null ? challenge.title.containsIgnoreCase(keyword) : null;
    }

    private BooleanExpression eqChallengeType(ChallengeType challengeType) {
        return challengeType != null ? challenge.challengeType.eq(challengeType) : null;
    }

    private BooleanExpression eqChallengeParticipationType(ChallengeParticipationType participationType) {
        return participationType != null ? challengePrice.challengeParticipationType.eq(participationType) : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? challenge.isVisible.eq(isVisible) : null;
    }

    private BooleanExpression isDayBeforeStartDate(int days) {
        LocalDate nowPlusDays = LocalDate.now().plusDays(days);
        return Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, '%Y-%m-%d')", challenge.startDate).eq(nowPlusDays);
    }

    private BooleanExpression isDayAfterEndDate(int days) {
        LocalDate nowMinusDays = LocalDate.now().minusDays(days);
        return Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, '%Y-%m-%d')", challenge.endDate).eq(nowMinusDays);
    }

    private BooleanExpression isHourBeforeStartDate(int hours) {
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LocalDateTime nowPlusHours = LocalDateTime.now().plusHours(hours);
        return challenge.startDate.gt(now).and(challenge.startDate.loe(nowPlusHours));
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression inChallengeClassification(List<ProgramClassification> typeList) {
        return (typeList == null || typeList.isEmpty()) ? null : challengeClassification.programClassification.in(typeList);
    }

    private BooleanBuilder inChallengeStatus(List<ProgramStatusType> statusList) {
        if (statusList == null || statusList.isEmpty())
            return null;
        LocalDateTime now = LocalDateTime.now();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        statusList.forEach(status -> booleanBuilder.or(addBooleanExpression(status, now)));
        return booleanBuilder;
    }

    private BooleanExpression addBooleanExpression(ProgramStatusType programStatusType, LocalDateTime now) {
        if (ProgramStatusType.PREV.equals(programStatusType))
            return challengePrevStatus(now);
        else if (ProgramStatusType.PROCEEDING.equals(programStatusType))
            return challengeProceedingStatus(now);
        else if (ProgramStatusType.POST.equals(programStatusType))
            return challengePostStatus(now);
        return null;
    }

    private BooleanExpression challengeRecruiting(LocalDateTime now) {
        return challenge.beginning.loe(now).and(challenge.deadline.goe(now));
    }

    private BooleanExpression challengePrevStatus(LocalDateTime now) {
        return challenge.beginning.lt(now);
    }

    private BooleanExpression challengeProceedingStatus(LocalDateTime now) {
        return challenge.startDate.loe(now).and(challenge.endDate.goe(now));
    }

    private BooleanExpression challengePostStatus(LocalDateTime now) {
        return challenge.deadline.lt(now);
    }

    private List<OrderSpecifier<?>> orderByRecommendCondition() {
        LocalDateTime now = LocalDateTime.now();
        BooleanExpression isRecruiting = challengeRecruiting(now);

        // isRecruiting 기준 정렬
        OrderSpecifier<Integer> recruitingOrder = new CaseBuilder()
                .when(isRecruiting).then(0)
                .otherwise(1)
                .asc();

        // Date 기준 정렬
        SimpleExpression<LocalDateTime> dateField = new CaseBuilder()
                .when(isRecruiting).then(challenge.deadline)
                .otherwise(challenge.createDate);
        OrderSpecifier<LocalDateTime> dateOrder = new OrderSpecifier<>(
                isRecruiting.equals(Expressions.TRUE) ? Order.ASC : Order.DESC,
                dateField);

        return List.of(recruitingOrder, dateOrder);
    }
}
