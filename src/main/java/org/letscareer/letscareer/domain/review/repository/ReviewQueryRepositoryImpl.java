package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.entity.Review;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QChallengeApplication.challengeApplication;
import static org.letscareer.letscareer.domain.application.entity.QLiveApplication.liveApplication;
import static org.letscareer.letscareer.domain.challenge.entity.QChallenge.challenge;
import static org.letscareer.letscareer.domain.live.entity.QLive.live;
import static org.letscareer.letscareer.domain.review.entity.QReview.review;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ReviewDetailVo> findReviewVo(Long reviewId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReviewDetailVo.class,
                        review.id,
                        review.nps,
                        review.npsAns,
                        review.npsCheckAns,
                        review.content,
                        review.score,
                        review.createDate
                ))
                .from(review)
                .where(
                        eqReviewId(reviewId)
                )
                .fetchOne()
        );
    }

    @Override
    public Page<ReviewVo> findChallengeReviewVos(Long challengeId, Pageable pageable) {
        List<ReviewVo> contents = queryFactory
                .select(Projections.constructor(ReviewVo.class,
                        user.name,
                        review.nps,
                        review.npsAns,
                        review.npsCheckAns,
                        review.content,
                        review.score,
                        review.createDate
                ))
                .from(review)
                .leftJoin(review.application, challengeApplication._super)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(review.application.user, user)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Review> countQuery = queryFactory
                .selectFrom(review)
                .leftJoin(review.application, challengeApplication._super)
                .leftJoin(challengeApplication.challenge, challenge)
                .leftJoin(review.application.user, user)
                .where(
                        eqChallengeId(challengeId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<ReviewVo> findLiveReviewVos(Long liveId, Pageable pageable) {
        List<ReviewVo> contents = queryFactory
                .select(Projections.constructor(ReviewVo.class,
                        user.name,
                        review.nps,
                        review.npsAns,
                        review.npsCheckAns,
                        review.content,
                        review.score,
                        review.createDate
                ))
                .from(review)
                .leftJoin(review.application, liveApplication._super)
                .leftJoin(liveApplication.live, live)
                .leftJoin(review.application.user, user)
                .where(
                        eqLiveId(liveId)
                )
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Review> countQuery = queryFactory
                .selectFrom(review)
                .leftJoin(review.application, liveApplication._super)
                .leftJoin(liveApplication.live, live)
                .leftJoin(review.application.user, user)
                .where(
                        eqLiveId(liveId)
                )
                .orderBy(review.id.desc());

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    public BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? review.id.eq(reviewId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }

    private BooleanExpression eqLiveId(Long challengeId) {
        return challengeId != null ? live.id.eq(challengeId) : null;
    }
}
