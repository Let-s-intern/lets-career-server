package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;
import org.letscareer.letscareer.domain.banner.vo.BannerUserVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.banner.entity.QMainBanner.mainBanner;

@RequiredArgsConstructor
public class MainBannerQueryRepositoryImpl implements MainBannerQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BannerAdminVo> findAllMainBannerAdminVos() {
        return queryFactory
                .select(Projections.constructor(BannerAdminVo.class,
                        mainBanner.id,
                        mainBanner.title,
                        mainBanner.link,
                        mainBanner.startDate,
                        mainBanner.endDate,
                        mainBanner.isValid,
                        mainBanner.isVisible,
                        mainBanner.imgUrl))
                .from(mainBanner)
                .orderBy(mainBanner.id.desc())
                .fetch();
    }

    @Override
    public List<BannerUserVo> findAllMainBannerUserVos() {
        return queryFactory
                .select(Projections.constructor(BannerUserVo.class,
                        mainBanner.id,
                        mainBanner.title,
                        mainBanner.link,
                        mainBanner.startDate,
                        mainBanner.endDate,
                        mainBanner.isValid,
                        mainBanner.imgUrl))
                .from(mainBanner)
                .where(
                        isVisible(),
                        isWithinDateRange()
                )
                .orderBy(mainBanner.id.desc())
                .fetch();
    }

    @Override
    public Optional<BannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(BannerAdminDetailVo.class,
                        mainBanner.id,
                        mainBanner.title,
                        mainBanner.link,
                        mainBanner.startDate,
                        mainBanner.endDate,
                        mainBanner.isValid,
                        mainBanner.isVisible,
                        mainBanner.imgUrl))
                .from(mainBanner)
                .where(
                        eqBannerId(bannerId)
                )
                .fetchFirst());
    }

    private BooleanExpression eqBannerId(Long bannerId) {
        return bannerId != null ? mainBanner.id.eq(bannerId) : null;
    }

    private BooleanExpression isVisible() {
        return mainBanner.isVisible.eq(true);
    }

    private BooleanExpression isWithinDateRange() {
        LocalDateTime now = LocalDateTime.now();
        return mainBanner.startDate.loe(now).and(mainBanner.endDate.goe(now));
    }
}
