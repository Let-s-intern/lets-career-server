package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.type.ChallengeType;
import org.letscareer.letscareer.domain.review.type.ReviewProgramType;
import org.letscareer.letscareer.domain.review.vo.ReviewInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.review.entity.QVWReview.vWReview;

@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewInfoVo> findAllReviewInfoVos(List<ReviewProgramType> typeList, List<ChallengeType> challengeTypeList, Pageable pageable) {
        List<ReviewInfoVo> reviewInfoVos = queryFactory
                .select(Projections.constructor(ReviewInfoVo.class,
                        vWReview.reviewId,
                        vWReview.type,
                        vWReview.createDate,
                        vWReview.goodPoint,
                        vWReview.badPoint,
                        vWReview.programTitle,
                        vWReview.programThumbnail,
                        vWReview.challengeType,
                        vWReview.missionTitle,
                        vWReview.missionTh,
                        vWReview.attendanceReview,
                        vWReview.userName,
                        vWReview.userWishJob,
                        vWReview.userWishCompany))
                .from(vWReview)
                .where(
                        inReviewProgramType(typeList),
                        inChallengeType(challengeTypeList)
                )
                .groupBy(
                        vWReview.type,
                        vWReview.reviewId
                )
                .orderBy(vWReview.createDate.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(vWReview.countDistinct())
                .from(vWReview)
                .where(
                        inReviewProgramType(typeList),
                        inChallengeType(challengeTypeList)
                )
                .groupBy(
                        vWReview.type,
                        vWReview.reviewId
                );

        return PageableExecutionUtils.getPage(reviewInfoVos, pageable, countQuery::fetchCount);
    }

    @Override
    public Long countReviews() {
        return queryFactory
                .select(vWReview.count())
                .from(vWReview)
                .fetchOne();
    }

    @Override
    public Boolean existReviewByApplicationId(Long applicationId) {
        return queryFactory
                .selectOne()
                .from(vWReview)
                .where(vWReview.applicationId.eq(applicationId))
                .fetchFirst() != null;
    }

    private BooleanExpression inReviewProgramType(List<ReviewProgramType> typeList) {
        if(typeList == null || typeList.isEmpty()) return null;
        BooleanExpression challengeReviewCondition = vWReview.type.eq(ReviewProgramType.CHALLENGE_REVIEW).and(vWReview.type.in(typeList));
        BooleanExpression liveReviewCondition = vWReview.type.eq(ReviewProgramType.LIVE_REVIEW).and(vWReview.type.in(typeList));
        BooleanExpression reportReviewCondition = vWReview.type.eq(ReviewProgramType.REPORT_REVIEW).and(vWReview.type.in(typeList));
        BooleanExpression missionReviewCondition = vWReview.type.eq(ReviewProgramType.MISSION_REVIEW).and(vWReview.type.in(typeList));
        return challengeReviewCondition.or(liveReviewCondition).or(reportReviewCondition).or(missionReviewCondition);
    }

    private BooleanExpression inChallengeType(List<ChallengeType> challengeTypeList) {
        if(challengeTypeList == null || challengeTypeList.isEmpty()) return null;
        BooleanExpression challengeReviewCondition = vWReview.type.eq(ReviewProgramType.CHALLENGE_REVIEW).and(vWReview.challengeType.in(challengeTypeList));
        BooleanExpression missionReviewCondition = vWReview.type.eq(ReviewProgramType.MISSION_REVIEW).and(vWReview.challengeType.in(challengeTypeList));
        return challengeReviewCondition.or(missionReviewCondition);
    }
}
