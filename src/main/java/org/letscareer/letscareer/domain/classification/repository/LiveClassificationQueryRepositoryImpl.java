package org.letscareer.letscareer.domain.classification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.vo.LiveClassificationVo;

import java.util.List;

import static org.letscareer.letscareer.domain.classification.entity.QLiveClassification.liveClassification;

@RequiredArgsConstructor
public class LiveClassificationQueryRepositoryImpl implements LiveClassificationQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<LiveClassificationVo> findLiveClassificationVos(Long liveId) {
        return jpaQueryFactory
                .select(Projections.constructor(LiveClassificationVo.class,
                        liveClassification.programClassification
                ))
                .from(liveClassification)
                .where(
                        eqLiveId(liveId)
                )
                .fetch();
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? liveClassification.id.eq(liveId) : null;
    }
}
