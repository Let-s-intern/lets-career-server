package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.review.entity.QChallengeReview.challengeReview;

@RequiredArgsConstructor
public class ChallengeReviewQueryRepositoryImpl implements ChallengeReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChallengeReviewAdminVo> findAllChallengeReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(ChallengeReviewAdminVo.class,
                        challengeReview.id,
                        challengeReview.createDate,
                        challengeReview.challengeType,
                        challengeReview.challenge.title,
                        challengeReview._super.application.user.name,
                        challengeReview.score,
                        challengeReview.npsScore,
                        challengeReview.goodPoint,
                        challengeReview.badPoint,
                        challengeReview.isVisible))
                .from(challengeReview)
                .orderBy(challengeReview.id.desc())
                .fetch();
    }

    @Override
    public Optional<ChallengeReviewVo> findChallengeReviewVoByReviewId(Long reviewId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(ChallengeReviewVo.class,
                                challengeReview.application.user.id,
                                challengeReview.id,
                                challengeReview.createDate,
                                challengeReview.challengeType,
                                challengeReview.challenge.title,
                                challengeReview.score,
                                challengeReview.npsScore,
                                challengeReview.goodPoint,
                                challengeReview.badPoint))
                        .from(challengeReview)
                        .where(
                                eqReviewId(reviewId)
                        )
                        .fetchFirst()
        );
    }

    private BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? challengeReview.id.eq(reviewId) : null;
    }
}
