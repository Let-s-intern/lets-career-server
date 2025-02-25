package org.letscareer.letscareer.domain.blog.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.blog.entity.Blog;
import org.letscareer.letscareer.domain.user.entity.User;

import java.util.List;

import static org.letscareer.letscareer.domain.blog.entity.QBlogLike.blogLike;

@RequiredArgsConstructor
public class LikeQueryRepositoryImpl implements LikeQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existsByBlogAndUser(Long blogId, Long userId) {
        Integer result = queryFactory
                .selectOne()
                .from(blogLike)
                .where(
                        eqBlogId(blogId),
                        eqUserId(userId)
                )
                .fetchFirst();
        return result != null;
    }
    
    @Override
    public List<Long> findUserLikedBlogIds(Long userId){
        return queryFactory
                .select(blogLike.blog.id)
                .from(blogLike)
                .where(
                        eqUserId(userId)
                )
                .fetch();
    }

    private BooleanExpression eqBlogId(Long blogId) {
        return blogId != null ? blogLike.blog.id.eq(blogId) : null;
    }

    private BooleanExpression eqUserId(Long userId){
        return userId != null ? blogLike.user.id.eq(userId) : null;
    }
}
