package org.letscareer.letscareer.domain.blogbanner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;

import java.util.List;

import static org.letscareer.letscareer.domain.blogbanner.entity.QBlogBanner.blogBanner;

@RequiredArgsConstructor
public class BlogBannerQueryRepositoryImpl implements BlogBannerQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AdminBlogBannerVo> findAdminBlogBannerVos(){
        return queryFactory
                .select(Projections.constructor(AdminBlogBannerVo.class,
                        blogBanner.id,
                        blogBanner.title,
                        blogBanner.link,
                        blogBanner.startDate,
                        blogBanner.endDate,
                        blogBanner.weight,
                        blogBanner.isVisible))
                .from(blogBanner)
                .orderBy(
                        blogBanner.id.desc()
                )
                .fetch();
    }
}
