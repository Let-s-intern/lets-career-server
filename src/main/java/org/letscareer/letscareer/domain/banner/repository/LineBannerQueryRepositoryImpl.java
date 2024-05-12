package org.letscareer.letscareer.domain.banner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.banner.vo.LineBannerAdminVo;

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
}
