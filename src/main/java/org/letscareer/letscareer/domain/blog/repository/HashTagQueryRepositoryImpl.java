package org.letscareer.letscareer.domain.blog.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.vo.HashTagDetailInfo;

import java.util.List;

import static org.letscareer.letscareer.domain.blog.entity.QBlog.blog;
import static org.letscareer.letscareer.domain.blog.entity.QBlogHashTag.blogHashTag;
import static org.letscareer.letscareer.domain.blog.entity.QHashTag.hashTag;

@RequiredArgsConstructor
public class HashTagQueryRepositoryImpl implements HashTagQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<HashTagDetailInfo> findAllHashTagDetailInfo(Long blogId) {
        return queryFactory
                .select(Projections.constructor(HashTagDetailInfo.class,
                        hashTag.id,
                        hashTag.title,
                        hashTag.createDate,
                        hashTag.lastModifiedDate))
                .from(hashTag)
                .leftJoin(hashTag.blogHashTags, blogHashTag)
                .leftJoin(blogHashTag.blog, blog)
                .where(
                        eqBlogId(blogId)
                )
                .orderBy(
                        hashTag.id.desc()
                )
                .groupBy(hashTag.id)
                .fetch();
    }

    private BooleanExpression eqBlogId(Long blogId) {
        return blogId != null ? blog.id.eq(blogId) : null;
    }
}
