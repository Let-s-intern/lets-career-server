package org.letscareer.letscareer.domain.review.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.review.vo.ReportReviewAdminVo;

import java.util.List;

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
}
