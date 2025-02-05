package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ChallengeReviewVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.review.entity.QChallengeReview.challengeReview;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

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
                        userNameExpression(),
                        challengeReview.score,
                        challengeReview.npsScore,
                        challengeReview.isVisible))
                .from(challengeReview)
                .leftJoin(challengeReview.application, application)
                .leftJoin(application.user, user)
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
                                challengeReview.npsScore))
                        .from(challengeReview)
                        .where(
                                eqReviewId(reviewId)
                        )
                        .fetchFirst()
        );
    }

    private StringExpression userNameExpression() {
        return new CaseBuilder()
                .when(application.isNotNull()).then(user.name)
                .otherwise("익명");
    }

    private BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? challengeReview.id.eq(reviewId) : null;
    }
}
