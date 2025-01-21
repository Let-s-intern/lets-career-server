package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.ReviewItemAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewItemVo;

import java.util.List;

import static org.letscareer.letscareer.domain.review.entity.QReviewItem.reviewItem;

@RequiredArgsConstructor
public class ReviewItemQueryRepositoryImpl implements ReviewItemQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReviewItemVo> findAllReviewItemVosByReviewId(Long reviewId, Boolean isVisible) {
        return queryFactory
                .select(Projections.constructor(ReviewItemVo.class,
                        reviewItem.id,
                        reviewItem.questionType,
                        reviewItem.answer))
                .from(reviewItem)
                .where(
                        eqReviewId(reviewId),
                        eqIsVisible(isVisible)
                )
                .orderBy(reviewItem.id.asc())
                .fetch();
    }

    @Override
    public List<ReviewItemAdminVo> findAllReviewItemAdminVosByReviewId(Long reviewId) {
        return queryFactory
                .select(Projections.constructor(ReviewItemAdminVo.class,
                        reviewItem.id,
                        reviewItem.questionType,
                        reviewItem.answer,
                        reviewItem.isVisible))
                .from(reviewItem)
                .where(
                        eqReviewId(reviewId)
                )
                .orderBy(reviewItem.id.asc())
                .fetch();
    }

    private BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? reviewItem.review.id.eq(reviewId) : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? reviewItem.isVisible.eq(isVisible) : null;
    }
}
