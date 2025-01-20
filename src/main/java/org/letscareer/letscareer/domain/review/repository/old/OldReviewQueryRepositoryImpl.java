package org.letscareer.letscareer.domain.review.repository.old;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.type.ProgramType;
import org.letscareer.letscareer.domain.review.entity.old.OldVWReview;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewDetailVo;
import org.letscareer.letscareer.domain.review.vo.old.OldReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.review.entity.old.QOldReview.oldReview;
import static org.letscareer.letscareer.domain.review.entity.old.QOldVWReview.oldVWReview;

@RequiredArgsConstructor
public class OldReviewQueryRepositoryImpl implements OldReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<OldReviewDetailVo> findReviewVo(Long reviewId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(OldReviewDetailVo.class,
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
    public Page<OldReviewAdminVo> findChallengeReviewAdminVos(Long challengeId, Pageable pageable) {
        List<OldReviewAdminVo> contents = queryFactory
                .select(Projections.constructor(OldReviewAdminVo.class,
                        oldVWReview.reviewId,
                        oldVWReview.applicationId,
                        oldVWReview.programTitle,
                        oldVWReview.programType,
                        oldVWReview.userId,
                        oldVWReview.userName,
                        oldVWReview.nps,
                        oldVWReview.npsAns,
                        oldVWReview.npsCheckAns,
                        oldVWReview.content,
                        oldVWReview.programDetail,
                        oldVWReview.score,
                        oldVWReview.isVisible,
                        oldVWReview.createDate
                ))
                .from(oldVWReview)
                .where(
                        eqChallengeId(challengeId)
                )
                .orderBy(oldVWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<OldVWReview> countQuery = queryFactory
                .selectFrom(oldVWReview)
                .where(
                        eqChallengeId(challengeId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<OldReviewVo> findChallengeReviewVos(Pageable pageable) {
        List<OldReviewVo> contents = queryFactory
                .select(Projections.constructor(OldReviewVo.class,
                        oldVWReview.programId,
                        oldVWReview.userName,
                        oldVWReview.content,
                        oldVWReview.score,
                        oldVWReview.createDate
                ))
                .from(oldVWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.CHALLENGE)
                )
                .orderBy(oldVWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(oldVWReview.reviewId.countDistinct())
                .from(oldVWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.CHALLENGE)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<OldReviewVo> findLiveReviewVos(Pageable pageable) {
        List<OldReviewVo> contents = queryFactory
                .select(Projections.constructor(OldReviewVo.class,
                        oldVWReview.programId,
                        oldVWReview.userName,
                        oldVWReview.content,
                        oldVWReview.score,
                        oldVWReview.createDate
                ))
                .from(oldVWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.LIVE)
                )
                .orderBy(oldVWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(oldVWReview.reviewId.countDistinct())
                .from(oldVWReview)
                .where(
                        eqIsVisible(),
                        eqProgramType(ProgramType.LIVE)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<OldReviewAdminVo> findLiveReviewAdminVos(Long liveId, Pageable pageable) {
        List<OldReviewAdminVo> contents = queryFactory
                .select(Projections.constructor(OldReviewAdminVo.class,
                        oldVWReview.reviewId,
                        oldVWReview.applicationId,
                        oldVWReview.programTitle,
                        oldVWReview.programType,
                        oldVWReview.userId,
                        oldVWReview.userName,
                        oldVWReview.nps,
                        oldVWReview.npsAns,
                        oldVWReview.npsCheckAns,
                        oldVWReview.content,
                        oldVWReview.programDetail,
                        oldVWReview.score,
                        oldVWReview.isVisible,
                        oldVWReview.createDate
                ))
                .from(oldVWReview)
                .where(
                        eqLiveId(liveId)
                )
                .orderBy(oldVWReview.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<OldVWReview> countQuery = queryFactory
                .selectFrom(oldVWReview)
                .where(
                        eqLiveId(liveId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public List<String> findReviewContentByLiveId(Long liveId) {
        return queryFactory
                .select(
                        oldVWReview.content
                )
                .from(oldVWReview)
                .where(
                        eqLiveId(liveId)
                )
                .fetch();
    }

    @Override
    public List<OldReviewAdminVo> findAllReviewAdminVosByProgramType(Boolean isVisible, ProgramType programType, List<String> sortBy) {
        List<OrderSpecifier<?>> orderSpecifiers = createReviewOrderSpecifierList(sortBy);
        List<OldReviewAdminVo> oldReviewAdminVos = queryFactory
                .select(Projections.constructor(OldReviewAdminVo.class,
                        oldVWReview.reviewId,
                        oldVWReview.applicationId,
                        oldVWReview.programTitle,
                        oldVWReview.programType,
                        oldVWReview.userId,
                        oldVWReview.userName,
                        oldVWReview.nps,
                        oldVWReview.npsAns,
                        oldVWReview.npsCheckAns,
                        oldVWReview.content,
                        oldVWReview.programDetail,
                        oldVWReview.score,
                        oldVWReview.isVisible,
                        oldVWReview.createDate
                        ))
                .from(oldVWReview)
                .where(
                        eqProgramType(programType),
                        eqIsVisible(isVisible)
                )
                .orderBy(
                        orderSpecifiers.toArray(OrderSpecifier[]::new)
                )
                .fetch();
        return oldReviewAdminVos;
    }

    private List<OrderSpecifier<?>> createReviewOrderSpecifierList(List<String> sortBy) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        for(String sort : sortBy) {
            String[] sortInfo = sort.split(";");
            String property = sortInfo[0];
            Order direction = sortInfo[1].equalsIgnoreCase("ASC") ? Order.ASC : Order.DESC;
            PathBuilder expression = new PathBuilder(OldReviewDetailVo.class, "vWReview");
            orderSpecifiers.add(new OrderSpecifier<>(direction, expression.get(property)));
        }
        return orderSpecifiers;
    }

    public BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? oldReview.id.eq(reviewId) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? oldVWReview.programType.eq(ProgramType.CHALLENGE).and(oldVWReview.programId.eq(challengeId)) : null;
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? oldVWReview.programType.eq(ProgramType.LIVE).and(oldVWReview.programId.eq(liveId)) : null;
    }

    private BooleanExpression eqIsVisible() {
        return oldVWReview.isVisible.eq(true);
    }

    private BooleanExpression eqProgramType(ProgramType programType) {
        return programType != null ? oldVWReview.programType.eq(programType) : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? oldVWReview.isVisible.eq(isVisible) : null;
    }
}
