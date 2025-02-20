package org.letscareer.letscareer.domain.blogbanner.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerDetailVo;
import org.letscareer.letscareer.domain.blogbanner.vo.AdminBlogBannerVo;
import org.letscareer.letscareer.domain.blogbanner.vo.BlogBannerVo;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.blogbanner.entity.QBlogBanner.blogBanner;

@RequiredArgsConstructor
public class BlogBannerQueryRepositoryImpl implements BlogBannerQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<BlogBannerVo> findBlogBannerVos(Pageable pageable) {
        List<BlogBannerVo> contents = queryFactory
                .select(Projections.constructor(BlogBannerVo.class,
                        blogBanner.id,
                        blogBanner.title,
                        blogBanner.link,
                        blogBanner.file,
                        blogBanner.startDate,
                        blogBanner.endDate,
                        blogBanner.weight,
                        blogBanner.isVisible))
                .from(blogBanner)
                .orderBy(
                        blogBanner.weight.desc()
                )
                .where(
                        eqIsVisible(true),
                        isActive()
                )
                .groupBy(blogBanner.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(blogBanner.id.countDistinct())
                .from(blogBanner)
                .where(
                        eqIsVisible(true),
                        isActive()
                )
                .groupBy(blogBanner.id);

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchOne);
    }

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

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? blogBanner.isVisible.eq(isVisible) : null;
    }

    private BooleanExpression isActive(){
        LocalDateTime now = LocalDateTime.now();
        return blogBanner.startDate.before(now).and(blogBanner.endDate.after(now));
    }
}
