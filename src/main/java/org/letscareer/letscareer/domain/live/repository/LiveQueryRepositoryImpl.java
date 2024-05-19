package org.letscareer.letscareer.domain.live.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.type.ProgramClassification;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;
import org.letscareer.letscareer.domain.live.vo.LiveProfileVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

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
    public Page<LiveProfileVo> findLiveProfileVos(ProgramClassification type, Pageable pageable) {
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
                        eqLiveClassification(type)
                )
                .orderBy(live.id.desc())
                .fetch();

        JPAQuery<Live> countQuery = jpaQueryFactory
                .selectFrom(live)
                .where(
                        eqLiveClassification(type)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? live.id.eq(liveId) : null;
    }

    private BooleanExpression eqLiveClassification(ProgramClassification type) {
        return type != null ? liveClassification.programClassification.eq(type) : null;
    }
}
