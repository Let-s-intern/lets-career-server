package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.BlogReviewAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.review.entity.QBlogReview.blogReview;

@RequiredArgsConstructor
public class BlogReviewQueryRepositoryImpl implements BlogReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<BlogReviewAdminVo> findAllBlogReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(BlogReviewAdminVo.class,
                        blogReview.id,
                        blogReview.postDate,
                        blogReview.programType,
                        blogReview.programTitle,
                        blogReview.name,
                        blogReview.title,
                        blogReview.url,
                        blogReview.thumbnail,
                        blogReview.isVisible))
                .from(blogReview)
                .orderBy(blogReview.id.desc())
                .fetch();
    }
}
