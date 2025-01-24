package org.letscareer.letscareer.domain.admincalssification.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.admincalssification.vo.VodAdminClassificationDetailVo;

import java.util.List;

import static org.letscareer.letscareer.domain.admincalssification.entity.QVodAdminClassification.vodAdminClassification;

@RequiredArgsConstructor
public class VodAdminClassificationRepositoryImpl implements VodAdminClassificationQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<VodAdminClassificationDetailVo> findVodClassificationDetailVos(Long vodId) {
        return jpaQueryFactory
                .select(Projections.constructor(VodAdminClassificationDetailVo.class,
                        vodAdminClassification.programAdminClassification
                ))
                .from(vodAdminClassification)
                .where(
                        eqVodId(vodId)
                )
                .fetch();
    }

    private BooleanExpression eqVodId(Long vodId) {
        return vodId != null ? vodAdminClassification.vod.id.eq(vodId) : null;
    }
}
