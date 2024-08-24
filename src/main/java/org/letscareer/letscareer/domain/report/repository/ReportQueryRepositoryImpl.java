package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationPaymentForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;
import static org.letscareer.letscareer.domain.application.entity.report.QReportFeedbackApplication.reportFeedbackApplication;
import static org.letscareer.letscareer.domain.coupon.entity.QCoupon.coupon;
import static org.letscareer.letscareer.domain.payment.entity.QPayment.payment;
import static org.letscareer.letscareer.domain.report.entity.QReport.report;
import static org.letscareer.letscareer.domain.report.entity.QReportFeedback.reportFeedback;
import static org.letscareer.letscareer.domain.report.entity.QReportOption.reportOption;
import static org.letscareer.letscareer.domain.report.entity.QReportPrice.reportPrice;
import static org.letscareer.letscareer.domain.user.entity.QUser.user;

@RequiredArgsConstructor
public class ReportQueryRepositoryImpl implements ReportQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReportForAdminVo> findReportForAdminVos(Pageable pageable) {
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

    @Override
    public Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, Pageable pageable) {
        List<ReportApplicationForAdminVo> contents = queryFactory
                .select(Projections.constructor(ReportApplicationForAdminVo.class,
                        reportApplication.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,
                        reportApplication.wishJob,
                        reportApplication.message,
                        reportApplication.status,
                        reportApplication.applyFile.url,
                        reportApplication.reportFile.url,
                        reportApplication.recruitmentFile.url,
                        reportApplication.isCanceled,
                        reportApplication.createDate
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .where(
                        eqReportId(reportId)
                )
                .orderBy(reportApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reportApplication.id.countDistinct())
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .where(
                        eqReportId(reportId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<ReportFeedbackApplicationForAdminVo> findReportFeedbackApplicationForAdminVos(Long reportId, Pageable pageable) {
        List<ReportFeedbackApplicationForAdminVo> contents = queryFactory
                .select(Projections.constructor(ReportFeedbackApplicationForAdminVo.class,
                        reportApplication.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,
                        reportApplication.wishJob,
                        reportApplication.message,
                        reportFeedbackApplication.reportFeedbackStatus,
                        reportApplication.applyFile.url,
                        reportApplication.reportFile.url,
                        reportFeedbackApplication.zoomLink,
                        reportFeedbackApplication.desiredDate1,
                        reportFeedbackApplication.desiredDate2,
                        reportFeedbackApplication.desiredDate3,
                        reportFeedbackApplication.desiredDateAdmin,
                        reportFeedbackApplication.desiredDateType,
                        reportApplication.isCanceled,
                        reportApplication.createDate
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .where(
                        eqReportId(reportId)
                )
                .orderBy(reportFeedbackApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reportFeedbackApplication.id.countDistinct())
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .where(
                        eqReportId(reportId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Optional<ReportApplicationPaymentForAdminVo> findReportApplicationPaymentForAdminVo(Long reportId, Long applicationId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportApplicationPaymentForAdminVo.class,
                        payment.id,
                        payment.orderId,
                        reportApplication.reportPriceType,
                        Expressions.constant(subQueryOptionTitles(reportId)),
                        reportFeedbackApplication.id,
                        coupon.name,
                        payment.finalPrice,
                        reportApplication.isCanceled
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.payment)
                .leftJoin(payment.coupon)
                .where(
                        eqReportId(reportId),
                        eqApplicationId(applicationId)
                )
                .fetchOne());
    }

    @Override
    public Optional<ReportDetailVo> findReportDetailVo(Long reportId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportDetailVo.class,
                        report.id,
                        report.title,
                        report.notice,
                        report.contents,
                        report.type))
                .from(report)
                .where(
                        eqReportId(reportId)
                )
                .fetchOne());
    }

    @Override
    public Optional<ReportPriceDetailVo> findReportPriceDetailVo(Long reportId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportPriceDetailVo.class,
                        report.id,
                        Expressions.constant(subQueryForReportPriceInfos(reportId)),
                        Expressions.constant(subQueryReportOptionInfos(reportId)),
                        Expressions.constant(subQueryFeedbackPriceInfo(reportId))
                ))
                .from(report)
                .where(
                        eqReportId(reportId)
                )
                .fetchOne());
    }

    @Override
    public Page<MyReportVo> findMyReportVos(Long userId, ReportType reportType, Pageable pageable) {
        List<MyReportVo> contents = queryFactory
                .select(Projections.constructor(MyReportVo.class,
                        report.id,
                        reportApplication.id,
                        report.title,
                        report.type,
                        reportApplication.status,
                        reportApplication.createDate,
                        reportApplication.reportDate
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.user, user)
                .where(
                        eqReportType(reportType),
                        eqUserId(userId)
                )
                .orderBy(reportApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reportApplication.id.countDistinct())
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.user, user)
                .where(
                        eqReportType(reportType),
                        eqUserId(userId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public Page<MyReportFeedbackVo> findMyReportFeedbackVos(Long userId, ReportType reportType, Pageable pageable) {
        List<MyReportFeedbackVo> contents = queryFactory
                .select(Projections.constructor(MyReportFeedbackVo.class,
                        report.id,
                        reportApplication.id,
                        report.title,
                        report.type,
                        reportFeedbackApplication.reportFeedbackStatus,
                        reportFeedbackApplication.zoomLink,
                        reportFeedbackApplication.zoomPassword,
                        reportFeedbackApplication.desiredDate1,
                        reportFeedbackApplication.desiredDate2,
                        reportFeedbackApplication.desiredDate3,
                        reportFeedbackApplication.
                        reportApplication.createDate,
                        getConfirmedTimeFor()
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.user, user)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .where(
                        eqReportType(reportType),
                        eqUserId(userId)
                )
                .orderBy(reportApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reportApplication.id.countDistinct())
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.user, user)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .where(
                        eqReportType(reportType),
                        eqUserId(userId)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
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

    private List<String> subQueryOptionTitles(Long reportId) {
        return queryFactory.select(Projections.constructor(String.class,
                        reportOption.title
                ))
                .from(report)
                .leftJoin(report.optionList, reportOption)
                .where(
                        eqReportId(reportId)
                )
                .fetch();
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

    private Expression<LocalDateTime> getConfirmedTimeFor() {
        return new CaseBuilder()
                .when(reportFeedbackApplication.desiredDateType.eq(ReportDesiredDateType.DESIRED_DATE_1))
                .then(reportFeedbackApplication.desiredDate1)
                .when(reportFeedbackApplication.desiredDateType.eq(ReportDesiredDateType.DESIRED_DATE_2))
                .then(reportFeedbackApplication.desiredDate2)
                .when(reportFeedbackApplication.desiredDateType.eq(ReportDesiredDateType.DESIRED_DATE_3))
                .then(reportFeedbackApplication.desiredDate3)
                .when(reportFeedbackApplication.desiredDateType.eq(ReportDesiredDateType.DESIRED_DATE_ADMIN))
                .then(reportFeedbackApplication.desiredDateAdmin)
                .otherwise((LocalDateTime) null);
    }

    private BooleanExpression eqReportId(Long reportId) {
        return reportId != null ? report.id.eq(reportId) : null;
    }

    private BooleanExpression eqApplicationId(Long applicationId) {
        return applicationId != null ? reportApplication.id.eq(applicationId) : null;
    }

    private BooleanExpression eqReportType(ReportType reportType) {
        return reportType != null ? report.type.eq(reportType) : null;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }
}
