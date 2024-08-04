package org.letscareer.letscareer.domain.blog.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.type.BlogType;
import org.letscareer.letscareer.domain.blog.vo.BlogDetailVo;
import org.letscareer.letscareer.domain.blog.vo.BlogThumbnailVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.letscareer.letscareer.domain.blog.entity.QBlog.blog;
import static org.letscareer.letscareer.domain.blog.entity.QBlogHashTag.blogHashTag;
import static org.letscareer.letscareer.domain.blog.entity.QHashTag.hashTag;

@RequiredArgsConstructor
public class BlogQueryRepositoryImpl implements BlogQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BlogDetailVo> findBlogDetailVo(Long blogId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(BlogDetailVo.class,
                        blog.id,
                        blog.title,
                        blog.category,
                        blog.thumbnail,
                        blog.description,
                        blog.content,
                        blog.ctaLink,
                        blog.ctaText,
                        blog.displayDate,
                        blog.createDate,
                        blog.lastModifiedDate
                ))
                .from(blog)
                .where(
                        eqBlogId(blogId)
                )
                .fetchOne()
        );
    }

    @Override
    public Page<BlogThumbnailVo> findBlogThumbnailVos(User user, BlogType type, Long tagId, Pageable pageable) {
        List<BlogThumbnailVo> contents = queryFactory
                .select(Projections.constructor(BlogThumbnailVo.class,
                        blog.id,
                        blog.title,
                        blog.category,
                        blog.thumbnail,
                        blog.description,
                        blog.isDisplayed,
                        blog.displayDate,
                        blog.createDate,
                        blog.lastModifiedDate
                ))
                .from(blog)
                .leftJoin(blog.blogHashTags, blogHashTag)
                .leftJoin(blogHashTag.hashTag, hashTag)
                .where(
                        eqBlogType(type),
                        eqTagId(tagId),
                        eqIsDisplayedForUserRole(user)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(blog.displayDate.desc())
                .distinct()
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(blog.id.countDistinct())
                .from(blog)
                .leftJoin(blog.blogHashTags, blogHashTag)
                .leftJoin(blogHashTag.hashTag, hashTag)
                .where(
                        eqBlogType(type),
                        eqTagId(tagId),
                        eqIsDisplayedForUserRole(user)
                )
                .distinct();

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private BooleanExpression eqBlogId(Long blogId) {
        return blogId != null ? blog.id.eq(blogId) : null;
    }

    private BooleanExpression eqBlogType(BlogType type) {
        return type != null ? blog.category.eq(type) : null;
    }

    private BooleanExpression eqTagId(Long tagId) {
        return tagId != null ? hashTag.id.eq(tagId) : null;
    }

    private BooleanExpression eqIsDisplayedForUserRole(User user) {
        if (Objects.isNull(user))
            return blog.isDisplayed.isTrue();
        else if (UserRole.USER.equals(user.getRole()))
            return blog.isDisplayed.isTrue();
        else
            return null;
    }
}
