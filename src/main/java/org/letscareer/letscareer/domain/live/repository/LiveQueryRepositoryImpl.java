package org.letscareer.letscareer.domain.live.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.vo.LiveDetailVo;

import java.util.Optional;

import static org.letscareer.letscareer.domain.live.entity.QLive.live;

@RequiredArgsConstructor
public class LiveQueryRepositoryImpl implements LiveQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<LiveDetailVo> findLiveDetailVo(Long liveId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(LiveDetailVo.class,
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

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? live.id.eq(liveId) : null;
    }
}
