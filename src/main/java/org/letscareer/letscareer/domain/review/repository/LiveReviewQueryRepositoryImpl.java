package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.LiveReviewAdminVo;

import java.util.List;

import static org.letscareer.letscareer.domain.review.entity.QLiveReview.liveReview;

@RequiredArgsConstructor
public class LiveReviewQueryRepositoryImpl implements LiveReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<LiveReviewAdminVo> findAllLiveReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(LiveReviewAdminVo.class,
                        liveReview.id,
                        liveReview.createDate,
                        liveReview.live.title,
                        liveReview._super.application.user.name,
                        liveReview.score,
                        liveReview.npsScore,
                        liveReview.goodPoint,
                        liveReview.badPoint,
                        liveReview.isVisible))
                .from(liveReview)
                .orderBy(liveReview.id.desc())
                .fetch();
    }
}
