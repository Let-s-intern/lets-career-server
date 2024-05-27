package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.LineBannerUserVo;

import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.banner.entity.QLineBanner.lineBanner;

@RequiredArgsConstructor
public class LineBannerQueryRepositoryImpl implements LineBannerQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<LineBannerAdminVo> findAllLineBannerAdminVos() {
        return queryFactory
                .select(Projections.constructor(LineBannerAdminVo.class,
                        lineBanner.id,
                        lineBanner.title,
                        lineBanner.link,
                        lineBanner.startDate,
                        lineBanner.endDate,
                        lineBanner.isValid,
                        lineBanner.isVisible,
                        lineBanner.contents,
                        lineBanner.colorCode,
                        lineBanner.textColorCode))
                .from(lineBanner)
                .orderBy(lineBanner.id.desc())
                .fetch();
    }

    @Override
    public List<LineBannerUserVo> findAllLineBannerUserVos() {
        return queryFactory
                .select(Projections.constructor(LineBannerUserVo.class,
                        lineBanner.id,
                        lineBanner.title,
                        lineBanner.link,
                        lineBanner.startDate,
                        lineBanner.endDate,
                        lineBanner.isValid,
                        lineBanner.contents,
                        lineBanner.colorCode,
                        lineBanner.textColorCode))
                .from(lineBanner)
                .where(
                        isVisible(),
                        isWithinDateRange()
                )
                .orderBy(lineBanner.id.desc())
                .fetch();
    }

    private BooleanExpression isVisible() {
        return lineBanner.isVisible.eq(true);
    }

    private BooleanExpression isWithinDateRange() {
        LocalDateTime now = LocalDateTime.now();
        return lineBanner.startDate.loe(now).and(lineBanner.endDate.goe(now));
    }
}
