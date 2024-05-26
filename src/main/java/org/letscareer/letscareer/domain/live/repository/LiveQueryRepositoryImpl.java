package org.letscareer.letscareer.domain.live.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.live.vo.LiveProfileVo;
import org.letscareer.letscareer.domain.program.type.ProgramStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.classification.entity.QLiveClassification.liveClassification;
import static org.letscareer.letscareer.domain.live.entity.QLive.live;

@RequiredArgsConstructor
public class LiveQueryRepositoryImpl implements LiveQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<LiveDetailVo> findLiveDetailVo(Long liveId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(LiveDetailVo.class,
                        live.id,
                        live.title,
                        live.shortDesc,
                        live.description,
                        live.participationCount,
                        live.thumbnail,
                        live.mentorName,
                        live.job,
                        live.place,
                        live.startDate,
                        live.endDate,
                        live.deadline,
                        live.progressType))
                .from(live)
                .where(
                        eqLiveId(liveId)
                )
                .fetchOne()
        );
    }

    @Override
    public Page<LiveProfileVo> findLiveProfileVos(List<ProgramClassification> typeList, List<ProgramStatusType> statusList, Pageable pageable) {
        List<LiveProfileVo> contents = jpaQueryFactory
                .select(Projections.constructor(LiveProfileVo.class,
                        live.id,
                        live.title,
                        live.shortDesc,
                        live.thumbnail,
                        live.startDate,
                        live.endDate,
                        live.deadline
                ))
                .from(live)
                .leftJoin(
                        live.classificationList, liveClassification
                )
                .where(
                        inLiveClassification(typeList),
                        inLiveStatus(statusList)
                )
                .orderBy(live.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Live> countQuery = jpaQueryFactory
                .selectFrom(live)
                .where(
                        inLiveClassification(typeList),
                        inLiveStatus(statusList)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? live.id.eq(liveId) : null;
    }

    private BooleanExpression inLiveClassification(List<ProgramClassification> typeList) {
        return (typeList == null || typeList.isEmpty()) ? null : liveClassification.programClassification.in(typeList);
    }

    private BooleanBuilder inLiveStatus(List<ProgramStatusType> statusList) {
        if (statusList == null || statusList.isEmpty())
            return null;
        LocalDateTime now = LocalDateTime.now();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        statusList.forEach(status -> booleanBuilder.or(addBooleanExpression(status, now)));
        return booleanBuilder;
    }

    private BooleanExpression addBooleanExpression(ProgramStatusType programStatusType, LocalDateTime now) {
        if (ProgramStatusType.PREV.equals(programStatusType))
            return livePrevStatus(now);
        else if (ProgramStatusType.PROCEEDING.equals(programStatusType))
            return liveProceedingStatus(now);
        else if (ProgramStatusType.POST.equals(programStatusType))
            return livePostStatus(now);
        return null;
    }

    private BooleanExpression livePrevStatus(LocalDateTime now) {
        return live.startDate.lt(now);
    }

    private BooleanExpression liveProceedingStatus(LocalDateTime now) {
        return live.startDate.loe(now).and(live.endDate.goe(now));
    }

    private BooleanExpression livePostStatus(LocalDateTime now) {
        return live.endDate.lt(now);
    }
}
