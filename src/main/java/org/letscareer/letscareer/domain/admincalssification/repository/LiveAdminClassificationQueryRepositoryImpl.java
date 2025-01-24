package org.letscareer.letscareer.domain.admincalssification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.vo.LiveAdminClassificationDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.admincalssification.entity.QLiveAdminClassification.liveAdminClassification;

@RequiredArgsConstructor
public class LiveAdminClassificationQueryRepositoryImpl implements LiveAdminClassificationQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LiveAdminClassificationDetailVo> findLiveClassificationVos(Long liveId) {
        return jpaQueryFactory
                .select(Projections.constructor(LiveAdminClassificationDetailVo.class,
                        liveAdminClassification.programAdminClassification
                ))
                .from(liveAdminClassification)
                .where(
                        eqLiveId(liveId)
                )
                .fetch();
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? liveAdminClassification.live.id.eq(liveId) : null;
    }
}
