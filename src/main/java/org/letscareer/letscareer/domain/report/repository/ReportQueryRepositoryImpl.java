package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;
import static org.letscareer.letscareer.domain.application.entity.report.QReportFeedbackApplication.reportFeedbackApplication;
import static org.letscareer.letscareer.domain.report.entity.QReport.report;
import static org.letscareer.letscareer.domain.report.entity.QReportFeedback.reportFeedback;
import static org.letscareer.letscareer.domain.report.entity.QReportOption.reportOption;
import static org.letscareer.letscareer.domain.report.entity.QReportPrice.reportPrice;

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

    @Override
    public Optional<ReportDetailForAdminVo> findReportDetailForAdminVo(Long reportId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportDetailForAdminVo.class,
                        report.id,
                        report.type,
                        report.title,
                        report.contents,
                        report.notice,
                        Expressions.constant(subQueryForReportPriceInfos(reportId)),
                        Expressions.constant(subQueryReportOptionInfos(reportId)),
                        Expressions.constant(subQueryFeedbackPriceInfo(reportId))
                ))
                .from(report)
                .where(
                        eqReportId(reportId)
                )
                .fetchOne()
        );
    }

    private List<ReportPriceVo> subQueryForReportPriceInfos(Long reportId) {
        return queryFactory.select(Projections.constructor(ReportPriceVo.class,
                        reportPrice.reportPriceType,
                        reportPrice.price,
                        reportPrice.discountPrice
                ))
                .from(report)
                .leftJoin(report.priceList, reportPrice)
                .where(
                        eqReportId(reportId)
                )
                .fetch();
    }

    private List<ReportOptionVo> subQueryReportOptionInfos(Long reportId) {
        return queryFactory.select(Projections.constructor(ReportOptionVo.class,
                        reportOption.id,
                        reportOption.price,
                        reportOption.discountPrice,
                        reportOption.title
                ))
                .from(report)
                .leftJoin(report.optionList, reportOption)
                .where(
                        eqReportId(reportId)
                )
                .fetch();
    }

    private FeedbackPriceVo subQueryFeedbackPriceInfo(Long reportId) {
        return queryFactory.select(Projections.constructor(FeedbackPriceVo.class,
                        reportFeedback.id,
                        reportFeedback.feedbackPrice,
                        reportFeedback.feedbackDiscountPrice
                ))
                .from(report)
                .leftJoin(report.reportFeedback, reportFeedback)
                .where(
                        eqReportId(reportId)
                )
                .fetchOne();
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

    private BooleanExpression eqReportId(Long reportId) {
        return reportId != null ? report.id.eq(reportId) : null;
    }
}
