package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;
import static org.letscareer.letscareer.domain.application.entity.report.QReportFeedbackApplication.reportFeedbackApplication;
import static org.letscareer.letscareer.domain.report.entity.QReport.report;

@RequiredArgsConstructor
public class ReportQueryRepositoryImpl implements ReportQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReportForAdminVo> findReportForAdminInfos(Pageable pageable) {
        List<ReportForAdminVo> contents = queryFactory
                .select(Projections.constructor(ReportForAdminVo.class,
                        report.id,
                        report.type,
                        report.title,
                        countNonRefundedApplications(),
                        countNonRefundedFeedbackApplications(),
                        report.visibleDate,
                        report.createDate
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .orderBy(report.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(report.id)
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(report.id.countDistinct())
                .from(report);

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    private NumberExpression<Long> countNonRefundedApplications() {
        return new CaseBuilder()
                .when(reportApplication.isCanceled.isFalse())
                .then(reportApplication.id)
                .otherwise((Long) null)
                .countDistinct();
    }

    private NumberExpression<Long> countNonRefundedFeedbackApplications() {
        return new CaseBuilder()
                .when(reportApplication.isCanceled.isFalse())
                .then(reportFeedbackApplication.id)
                .otherwise((Long) null)
                .countDistinct();
    }
}
