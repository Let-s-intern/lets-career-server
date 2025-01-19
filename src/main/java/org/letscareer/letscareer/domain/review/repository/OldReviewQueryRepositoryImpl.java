package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.entity.old.VWReview;
import org.letscareer.letscareer.domain.review.vo.ReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.review.entity.old.QOldReview.oldReview;
import static org.letscareer.letscareer.domain.review.entity.old.QVWReview.vWReview;

@RequiredArgsConstructor
public class OldReviewQueryRepositoryImpl implements OldReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ReviewDetailVo> findReviewVo(Long reviewId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReviewDetailVo.class,
                        oldReview.id,
                        oldReview.application.id,
                        oldReview.application.user.id,
                        oldReview.nps,
                        oldReview.npsAns,
                        oldReview.npsCheckAns,
                        oldReview.content,
                        oldReview.score,
                        oldReview.programDetail,
                        oldReview.createDate
                ))
                .from(oldReview)
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
                        vWReview.applicationId,
                        vWReview.programTitle,
                        vWReview.programType,
                        vWReview.userId,
                        vWReview.userName,
                        vWReview.nps,
                        vWReview.npsAns,
                        vWReview.npsCheckAns,
                        vWReview.content,
                        vWReview.programDetail,
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
                        vWReview.programId,
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
                        vWReview.programId,
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
                        vWReview.applicationId,
                        vWReview.programTitle,
                        vWReview.programType,
                        vWReview.userId,
                        vWReview.userName,
                        vWReview.nps,
                        vWReview.npsAns,
                        vWReview.npsCheckAns,
                        vWReview.content,
                        vWReview.programDetail,
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

    @Override
    public List<String> findReviewContentByLiveId(Long liveId) {
        return queryFactory
                .select(
                        vWReview.content
                )
                .from(vWReview)
                .where(
                        eqLiveId(liveId)
                )
                .fetch();
    }

    @Override
    public List<ReviewAdminVo> findAllReviewAdminVosByProgramType(Boolean isVisible, ProgramType programType, List<String> sortBy) {
        List<OrderSpecifier<?>> orderSpecifiers = createReviewOrderSpecifierList(sortBy);
        List<ReviewAdminVo> reviewAdminVos = queryFactory
                .select(Projections.constructor(ReviewAdminVo.class,
                        vWReview.reviewId,
                        vWReview.applicationId,
                        vWReview.programTitle,
                        vWReview.programType,
                        vWReview.userId,
                        vWReview.userName,
                        vWReview.nps,
                        vWReview.npsAns,
                        vWReview.npsCheckAns,
                        vWReview.content,
                        vWReview.programDetail,
                        vWReview.score,
                        vWReview.isVisible,
                        vWReview.createDate
                        ))
                .from(vWReview)
                .where(
                        eqProgramType(programType),
                        eqIsVisible(isVisible)
                )
                .orderBy(
                        orderSpecifiers.toArray(OrderSpecifier[]::new)
                )
                .fetch();
        return reviewAdminVos;
    }

    private List<OrderSpecifier<?>> createReviewOrderSpecifierList(List<String> sortBy) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        for(String sort : sortBy) {
            String[] sortInfo = sort.split(";");
            String property = sortInfo[0];
            Order direction = sortInfo[1].equalsIgnoreCase("ASC") ? Order.ASC : Order.DESC;
            PathBuilder expression = new PathBuilder(ReviewDetailVo.class, "vWReview");
            orderSpecifiers.add(new OrderSpecifier<>(direction, expression.get(property)));
        }
        return orderSpecifiers;
    }

    public BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? oldReview.id.eq(reviewId) : null;
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

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? vWReview.isVisible.eq(isVisible) : null;
    }
}
