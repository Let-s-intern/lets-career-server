package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public Page<ReviewInfoVo> findAllReviewInfoVos(Pageable pageable) {
        List<ReviewInfoVo> reviewInfoVos = queryFactory
                .select(Projections.constructor(ReviewInfoVo.class,
                        vWReview.reviewId,
                        vWReview.type,
                        vWReview.createDate,
                        vWReview.goodPoint,
                        vWReview.badPoint,
                        vWReview.programTitle,
                        vWReview.programThumbnail,
                        vWReview.missionTitle,
                        vWReview.missionTh,
                        vWReview.userName,
                        vWReview.userWishJob,
                        vWReview.userWishCompany))
                .from(vWReview)
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
                .groupBy(
                        vWReview.type,
                        vWReview.reviewId
                );

        return PageableExecutionUtils.getPage(reviewInfoVos, pageable, countQuery::fetchOne);
    }
}
