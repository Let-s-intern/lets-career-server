package org.letscareer.letscareer.domain.curation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.CurationAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.curation.entity.QCuration.curation;

@RequiredArgsConstructor
public class CurationQueryRepositoryImpl implements CurationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CurationAdminVo> findCurationAdminVosByLocationType(CurationLocationType locationType) {
        return queryFactory
                .select(Projections.constructor(CurationAdminVo.class,
                        curation.id,
                        curation.locationType,
                        curation.title,
                        curation.startDate,
                        curation.endDate,
                        curation.isVisible))
                .from(curation)
                .where(
                        eqLocationType(locationType)
                )
                .orderBy(
                        curation.id.desc()
                )
                .fetch();
    }

    private BooleanExpression eqLocationType(CurationLocationType locationType) {
        return locationType != null ? curation.locationType.eq(locationType) : null;
    }
}
