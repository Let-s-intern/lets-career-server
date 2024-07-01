package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.entity.VWReview;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.review.entity.QReview.review;
import static org.letscareer.letscareer.domain.review.entity.QVWReview.vWReview;

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
    public Page<ReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable) {
        List<ReviewAdminVo> contents = queryFactory
                .select(Projections.constructor(ReviewAdminVo.class,
                        vWReview.reviewId,
                        vWReview.userName,
                        vWReview.nps,
                        vWReview.npsAns,
                        vWReview.npsCheckAns,
                        vWReview.content,
                        vWReview.score,
                        vWReview.isVisible,
                        vWReview.createDate
                ))
                .from(vWReview)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(vWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<VWReview> countQuery = queryFactory
                .selectFrom(vWReview)
                .where(
                        eqChallengeId(challengeId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<ReviewVo> findChallengeReviewVos(Pageable pageable) {
        List<ReviewVo> contents = queryFactory
                .select(Projections.constructor(ReviewVo.class,
                        vWReview.userName,
                        vWReview.content,
                        vWReview.score,
                        vWReview.createDate
                ))
                .from(vWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.CHALLENGE)
                )
                .orderBy(vWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(vWReview.reviewId.countDistinct())
                .from(vWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.CHALLENGE)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<ReviewVo> findLiveReviewVos(Pageable pageable) {
        List<ReviewVo> contents = queryFactory
                .select(Projections.constructor(ReviewVo.class,
                        vWReview.userName,
                        vWReview.content,
                        vWReview.score,
                        vWReview.createDate
                ))
                .from(vWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.LIVE)
                )
                .orderBy(vWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(vWReview.reviewId.countDistinct())
                .from(vWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.LIVE)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<ReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable) {
        List<ReviewAdminVo> contents = queryFactory
                .select(Projections.constructor(ReviewAdminVo.class,
                        vWReview.reviewId,
                        vWReview.userName,
                        vWReview.nps,
                        vWReview.npsAns,
                        vWReview.npsCheckAns,
                        vWReview.content,
                        vWReview.score,
                        vWReview.isVisible,
                        vWReview.createDate
                ))
                .from(vWReview)
                .where(
                        eqLiveId(liveId)
                )
                .orderBy(vWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<VWReview> countQuery = queryFactory
                .selectFrom(vWReview)
                .where(
                        eqLiveId(liveId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    public BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? review.id.eq(reviewId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? vWReview.programType.eq(ProgramType.CHALLENGE).and(vWReview.programId.eq(challengeId)) : null;
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? vWReview.programType.eq(ProgramType.LIVE).and(vWReview.programId.eq(liveId)) : null;
    }

    private BooleanExpression eqIsVisible() {
        return vWReview.isVisible.eq(true);
    }

    private BooleanExpression eqProgramType(ProgramType programType) {
        return programType != null ? vWReview.programType.eq(programType) : null;
    }
}
