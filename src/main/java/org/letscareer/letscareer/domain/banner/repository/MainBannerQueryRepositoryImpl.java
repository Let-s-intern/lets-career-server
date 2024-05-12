package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.BannerAdminVo;

import java.util.List;

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
}
