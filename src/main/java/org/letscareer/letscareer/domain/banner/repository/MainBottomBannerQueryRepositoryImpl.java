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

import static org.letscareer.letscareer.domain.banner.entity.QMainBottomBanner.mainBottomBanner;

@RequiredArgsConstructor
public class MainBottomBannerQueryRepositoryImpl implements MainBottomBannerQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BannerAdminVo> findAllMainBottomBannerAdminVos() {
        return queryFactory
                .select(Projections.constructor(BannerAdminVo.class,
                        mainBottomBanner.id,
                        mainBottomBanner.title,
                        mainBottomBanner.link,
                        mainBottomBanner.startDate,
                        mainBottomBanner.endDate,
                        mainBottomBanner.isValid,
                        mainBottomBanner.isVisible,
                        mainBottomBanner.file.url,
                        mainBottomBanner.mobileFile.url
                ))
                .from(mainBottomBanner)
                .orderBy(mainBottomBanner.id.desc())
                .fetch();
    }

    @Override
    public List<BannerUserVo> findAllMainBottomBannerUserVos() {
        return queryFactory
                .select(Projections.constructor(BannerUserVo.class,
                        mainBottomBanner.id,
                        mainBottomBanner.title,
                        mainBottomBanner.link,
                        mainBottomBanner.startDate,
                        mainBottomBanner.endDate,
                        mainBottomBanner.isValid,
                        mainBottomBanner.file.url,
                        mainBottomBanner.mobileFile.url
                ))
                .from(mainBottomBanner)
                .where(
                        isVisible(),
                        isWithinDateRange()
                )
                .orderBy(mainBottomBanner.id.desc())
                .fetch();
    }

    @Override
    public Optional<BannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(BannerAdminDetailVo.class,
                        mainBottomBanner.id,
                        mainBottomBanner.title,
                        mainBottomBanner.link,
                        mainBottomBanner.startDate,
                        mainBottomBanner.endDate,
                        mainBottomBanner.isValid,
                        mainBottomBanner.isVisible,
                        mainBottomBanner.file.url,
                        mainBottomBanner.mobileFile.url
                ))
                .from(mainBottomBanner)
                .where(
                        eqBannerId(bannerId)
                )
                .fetchFirst());
    }

    private BooleanExpression eqBannerId(Long bannerId) {
        return bannerId != null ? mainBottomBanner.id.eq(bannerId) : null;
    }

    private BooleanExpression isVisible() {
        return mainBottomBanner.isVisible.eq(true);
    }

    private BooleanExpression isWithinDateRange() {
        LocalDateTime now = LocalDateTime.now();
        return mainBottomBanner.startDate.loe(now).and(mainBottomBanner.endDate.goe(now));
    }
}
