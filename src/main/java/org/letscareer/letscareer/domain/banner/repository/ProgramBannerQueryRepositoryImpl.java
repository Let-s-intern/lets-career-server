package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminDetailVo;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.banner.entity.QProgramBanner.programBanner;
import static org.letscareer.letscareer.domain.file.entity.QFile.file;

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

    private BooleanExpression eqBannerId(Long bannerId) {
        return bannerId != null ? programBanner.id.eq(bannerId) : null;
    }
}
