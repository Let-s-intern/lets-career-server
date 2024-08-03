package org.letscareer.letscareer.domain.blog.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.vo.RatingDetailVo;
import org.letscareer.letscareer.domain.blog.vo.RatingVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.blog.entity.QBlog.blog;
import static org.letscareer.letscareer.domain.blog.entity.QBlogRating.blogRating;

@RequiredArgsConstructor
public class RatingQueryRepositoryImpl implements RatingQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<RatingDetailVo> findAllRatingDetailVosByBlogId(Long blogId) {
        return queryFactory
                .select(Projections.constructor(RatingDetailVo.class,
                        blogRating.id,
                        blogRating.title,
                        blogRating.content,
                        blogRating.score,
                        blogRating.createDate,
                        blogRating.lastModifiedDate))
                .from(blogRating)
                .leftJoin(blogRating.blog, blog)
                .where(
                        eqBlogId(blogId)
                )
                .fetch();
    }

    @Override
    public Page<RatingVo> findRatingVos(Pageable pageable) {
        List<RatingVo> contents = queryFactory
                .select(Projections.constructor(RatingVo.class,
                        blogRating.id,
                        blog.title,
                        blog.category,
                        blogRating.title,
                        blogRating.score,
                        blogRating.createDate,
                        blogRating.lastModifiedDate))
                .from(blogRating)
                .leftJoin(blogRating.blog, blog)
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(blogRating.id.countDistinct())
                .from(blogRating)
                .distinct();

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqBlogId(Long blogId) {
        return blogId != null ? blog.id.eq(blogId) : null;
    }
}
