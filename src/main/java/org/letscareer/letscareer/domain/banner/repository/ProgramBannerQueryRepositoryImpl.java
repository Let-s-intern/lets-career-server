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

import static org.letscareer.letscareer.domain.banner.entity.QProgramBanner.programBanner;

@RequiredArgsConstructor
public class ProgramBannerQueryRepositoryImpl implements ProgramBannerQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BannerAdminVo> findAllProgramBannerAdminVos() {
        return queryFactory
                .select(Projections.constructor(BannerAdminVo.class,
                        programBanner.id,
                        programBanner.title,
                        programBanner.link,
                        programBanner.startDate,
                        programBanner.endDate,
                        programBanner.isValid,
                        programBanner.isVisible,
                        programBanner.file.url,
                        programBanner.mobileFile.url
                ))
                .from(programBanner)
                .orderBy(programBanner.id.desc())
                .fetch();
    }

    @Override
    public Optional<BannerAdminDetailVo> findBannerAdminDetailVo(Long bannerId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(BannerAdminDetailVo.class,
                                programBanner.id,
                                programBanner.title,
                                programBanner.link,
                                programBanner.startDate,
                                programBanner.endDate,
                                programBanner.isValid,
                                programBanner.isVisible,
                                programBanner.file.url,
                                programBanner.mobileFile.url
                        ))
                        .from(programBanner)
                        .where(
                                eqBannerId(bannerId)
                        )
                        .fetchFirst()
        );
    }

    @Override
    public List<BannerUserVo> findAllProgramBannerUserVos() {
        return queryFactory
                .select(Projections.constructor(BannerUserVo.class,
                        programBanner.id,
                        programBanner.title,
                        programBanner.link,
                        programBanner.startDate,
                        programBanner.endDate,
                        programBanner.isValid,
                        programBanner.file.url,
                        programBanner.mobileFile.url
                ))
                .from(programBanner)
                .where(
                        isVisible(),
                        isWithinDateRange()
                )
                .orderBy(programBanner.id.desc())
                .fetch();
    }

    private BooleanExpression isVisible() {
        return programBanner.isVisible.eq(true);
    }

    private BooleanExpression isWithinDateRange() {
        LocalDateTime now = LocalDateTime.now();
        return programBanner.startDate.loe(now).and(programBanner.endDate.goe(now));
    }

    private BooleanExpression eqBannerId(Long bannerId) {
        return bannerId != null ? programBanner.id.eq(bannerId) : null;
    }
}
