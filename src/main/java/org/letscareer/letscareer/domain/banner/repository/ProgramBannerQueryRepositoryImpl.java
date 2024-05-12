package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;

import java.util.List;

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
                        programBanner.imgUrl))
                .from(programBanner)
                .orderBy(programBanner.id.desc())
                .fetch();
    }
}
