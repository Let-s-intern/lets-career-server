package org.letscareer.letscareer.domain.blogbanner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<AdminBlogBannerDetailVo> findAdminBlogBannerDetailVoById(Long blogBannerId){
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(AdminBlogBannerDetailVo.class,
                        blogBanner.id,
                        blogBanner.title,
                        blogBanner.link,
                        blogBanner.file,
                        blogBanner.startDate,
                        blogBanner.endDate,
                        blogBanner.weight,
                        blogBanner.isVisible))
                .from(blogBanner)
                .where(
                        eqBlogBannerId(blogBannerId)
                )
                .fetchFirst());
    }

    private BooleanExpression eqBlogBannerId(Long blogBannerId) {
        return blogBannerId != null ? blogBanner.id.eq(blogBannerId) : null;
    }
}
