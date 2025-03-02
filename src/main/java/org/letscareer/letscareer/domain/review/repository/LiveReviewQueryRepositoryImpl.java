package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.dto.response.GetLiveMentorReviewResponseDto;
import org.letscareer.letscareer.domain.review.type.ReviewQuestionType;
import org.letscareer.letscareer.domain.review.vo.LiveReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.LiveReviewVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.QApplication.application;
import static org.letscareer.letscareer.domain.review.entity.QLiveReview.liveReview;
import static org.letscareer.letscareer.domain.review.entity.QReview.review;
import static org.letscareer.letscareer.domain.review.entity.QReviewItem.reviewItem;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class LiveReviewQueryRepositoryImpl implements LiveReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<LiveReviewAdminVo> findAllLiveReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(LiveReviewAdminVo.class,
                        liveReview.live.id,
                        liveReview.id,
                        liveReview.createDate,
                        liveReview.live.title,
                        userNameExpression(),
                        liveReview.score,
                        liveReview.npsScore,
                        liveReview.isVisible))
                .from(liveReview)
                .leftJoin(liveReview.application, application)
                .leftJoin(application.user, user)
                .orderBy(liveReview.id.desc())
                .fetch();
    }

    @Override
    public Optional<LiveReviewVo> findLiveReviewVoByReviewId(Long reviewId) {
        return Optional.ofNullable(
                queryFactory
                        .select(Projections.constructor(LiveReviewVo.class,
                                liveReview.application.user.id,
                                liveReview.live.id,
                                liveReview.id,
                                liveReview.createDate,
                                liveReview.live.title,
                                liveReview.score,
                                liveReview.npsScore))
                        .from(liveReview)
                        .where(
                                eqReviewId(reviewId)
                        )
                        .fetchFirst()
        );
    }

    @Override
    public List<GetLiveMentorReviewResponseDto> findLiveReviewByLiveId(Long liveId) {
        return queryFactory
                .select(Projections.constructor(GetLiveMentorReviewResponseDto.class,
                        reviewItem.questionType,
                        reviewItem.answer))
                .from(liveReview)
                .leftJoin(liveReview.reviewItemList, reviewItem)
                .where(
                        eqLiveId(liveId),
                        eqReviewQuestionType(ReviewQuestionType.GOOD_POINT)
                )
                .fetch();
    }

    private StringExpression userNameExpression() {
        return new CaseBuilder()
                .when(application.isNotNull()).then(user.name)
                .otherwise("익명");
    }

    private BooleanExpression eqReviewId(Long reviewId) {
        return reviewId != null ? liveReview.id.eq(reviewId) : null;
    }

    private BooleanExpression eqLiveId(Long liveId) {
        return liveId != null ? liveReview.live.id.eq(liveId) : null;
    }

    private BooleanExpression eqReviewQuestionType(ReviewQuestionType questionType) {
        return questionType != null ? reviewItem.questionType.eq(questionType) : null;
    }
}
