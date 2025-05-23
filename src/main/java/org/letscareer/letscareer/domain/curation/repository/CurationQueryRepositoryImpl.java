package org.letscareer.letscareer.domain.curation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.curation.type.CurationLocationType;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationDetailVo;
import org.letscareer.letscareer.domain.curation.vo.AdminCurationVo;
import org.letscareer.letscareer.domain.curation.vo.CurationVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.curation.entity.QCuration.curation;

@RequiredArgsConstructor
public class CurationQueryRepositoryImpl implements CurationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminCurationVo> findAdminCurationVosByLocationType(CurationLocationType locationType) {
        return queryFactory
                .select(Projections.constructor(AdminCurationVo.class,
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

    @Override
    public Optional<AdminCurationDetailVo> findAdminCurationDetailVoById(Long curationId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(AdminCurationDetailVo.class,
                        curation.id,
                        curation.locationType,
                        curation.title,
                        curation.subTitle,
                        curation.moreUrl,
                        curation.showImminentList,
                        curation.startDate,
                        curation.endDate,
                        curation.isVisible))
                .from(curation)
                .where(
                        eqCurationId(curationId)
                )
                .fetchFirst());
    }

    @Override
    public List<CurationVo> findAllCurationVosByLocationType(CurationLocationType locationType) {
        return queryFactory
                .select(Projections.constructor(CurationVo.class,
                        curation.id,
                        curation.locationType,
                        curation.title,
                        curation.subTitle,
                        curation.moreUrl,
                        curation.showImminentList,
                        curation.startDate,
                        curation.endDate))
                .from(curation)
                .where(
                        eqLocationType(locationType),
                        eqIsVisible(true),
                        isVisibleDuration()
                )
                .orderBy(
                        curation.id.desc()
                )
                .fetch();
    }

    private BooleanExpression eqCurationId(Long curationId) {
        return curationId != null ? curation.id.eq(curationId) : null;
    }

    private BooleanExpression eqLocationType(CurationLocationType locationType) {
        return locationType != null ? curation.locationType.eq(locationType) : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? curation.isVisible.eq(isVisible) : null;
    }

    private BooleanExpression isVisibleDuration() {
        LocalDateTime now = LocalDateTime.now();
        return curation.startDate.loe(now).and(curation.endDate.goe(now));
    }
}
