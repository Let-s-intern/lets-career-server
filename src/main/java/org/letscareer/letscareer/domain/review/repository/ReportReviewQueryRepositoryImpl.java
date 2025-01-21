package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.ReportReviewAdminVo;
import org.letscareer.letscareer.domain.review.vo.ReportReviewVo;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.review.entity.QReportReview.reportReview;

@RequiredArgsConstructor
public class ReportReviewQueryRepositoryImpl implements ReportReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ReportReviewAdminVo> findAllReportReviewAdminVos() {
        return queryFactory
                .select(Projections.constructor(ReportReviewAdminVo.class,
                        reportReview.id,
                        reportReview.createDate,
                        reportReview.reportType,
                        reportReview.report.title,
                        reportReview._super.application.user.name,
                        reportReview.score,
                        reportReview.npsScore,
                        reportReview.goodPoint,
                        reportReview.badPoint,
                        reportReview.isVisible))
                .from(reportReview)
                .orderBy(reportReview.id.desc())
                .fetch();
    }

    @Override
    public Optional<ReportReviewVo> findReportReviewByReviewId(Long reviewId) {
        return Optional.ofNullable(
                queryFactory
                    .select(Projections.constructor(ReportReviewVo.class,
                            reportReview.application.user.id,
                            reportReview.id,
                            reportReview.createDate,
                            reportReview.reportType,
                            reportReview.report.title,
                            reportReview.score,
                            reportReview.npsScore,
                            reportReview.goodPoint,
                            reportReview.badPoint))
                        .from(reportReview)
                        .where(
                                eqReportId(reviewId)
                        )
                        .fetchFirst()
        );
    }

    private BooleanExpression eqReportId(Long reviewId) {
        return reviewId != null ? reportReview.id.eq(reviewId) : null;
    }
}
