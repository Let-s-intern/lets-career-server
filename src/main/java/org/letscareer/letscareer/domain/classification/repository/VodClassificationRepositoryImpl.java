package org.letscareer.letscareer.domain.classification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.classification.vo.VodClassificationDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.classification.entity.QVodClassification.vodClassification;

@RequiredArgsConstructor
public class VodClassificationRepositoryImpl implements VodClassificationQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VodClassificationDetailVo> findVodClassificationDetailVos(Long vodId) {
        return jpaQueryFactory
                .select(Projections.constructor(VodClassificationDetailVo.class,
                        vodClassification.programClassification
                ))
                .from(vodClassification)
                .where(
                        eqVodId(vodId)
                )
                .fetch();
    }

    private BooleanExpression eqVodId(Long vodId) {
        return vodId != null ? vodClassification.id.eq(vodId) : null;
    }
}
