package org.letscareer.letscareer.domain.report.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
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
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;
import static org.letscareer.letscareer.domain.application.entity.report.QReportApplicationOption.reportApplicationOption;
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
    public Optional<Report> existByReportTypeAndVisibleDate(ReportType reportType) {
        return Optional.ofNullable(queryFactory
                .selectFrom(report)
                .where(
                        eqReportType(reportType),
                        isVisible()
                )
                .orderBy(report.id.desc())
                .fetchFirst());
    }

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
                        Expressions.constant(subQueryReportOptionInfosForAdmin(reportId)),
                        Expressions.constant(subQueryFeedbackPriceInfo(reportId)),
                        report.visibleDate
                ))
                .from(report)
                .where(
                        eqReportId(reportId)
                )
                .fetchOne()
        );
    }

    @Override
    public Page<ReportApplicationForAdminVo> findReportApplicationForAdminVos(Long reportId, ReportType reportType, ReportPriceType priceType, Boolean isApplyFeedback, Pageable pageable) {
        List<ReportApplicationForAdminVo> contents = queryFactory
                .select(Projections.constructor(ReportApplicationForAdminVo.class,
                        reportApplication.id,
                        user.name,
                        user.contactEmail,
                        user.phoneNum,

                        reportApplication.wishJob,
                        reportApplication.message,
                        reportApplication.status,
                        reportApplication.applyUrl,
                        reportApplication.reportUrl,
                        reportApplication.recruitmentUrl,

                        reportFeedbackApplication.id,
                        reportFeedbackApplication.reportFeedbackStatus,
                        reportFeedbackApplication.zoomLink,
                        reportFeedbackApplication.desiredDate1,
                        reportFeedbackApplication.desiredDate2,
                        reportFeedbackApplication.desiredDate3,
                        reportFeedbackApplication.desiredDateAdmin,
                        reportFeedbackApplication.desiredDateType,
                        reportApplication.createDate,

                        payment.id,
                        payment.orderId,
                        reportApplication.reportPriceType,
                        coupon.name,
                        payment.finalPrice,
                        reportApplication.isCanceled
                ))
                .from(reportApplication)
                .leftJoin(reportApplication.user, user)
                .leftJoin(reportApplication.report, report)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .leftJoin(reportApplication.payment, payment)
                .leftJoin(payment.coupon, coupon)
                .where(
                        eqReportId(reportId),
                        eqPriceType(priceType),
                        eqReportType(reportType),
                        isApplyFeedback(isApplyFeedback)
                )
                .orderBy(reportApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(reportApplication.id.countDistinct())
                .from(reportApplication)
                .leftJoin(reportApplication.report, report)
                .where(
                        eqReportId(reportId),
                        eqPriceType(priceType),
                        eqReportType(reportType)
                );

        return PageableExecutionUtils.getPage(contents, pageable, countQuery::fetchCount);
    }

    @Override
    public List<ReportApplicationOptionForAdminVo> findReportApplicationPaymentForAdminVos(Long reportId, Long applicationId, ReportType reportType, ReportPriceType priceType, String code) {
        return queryFactory
                .select(Projections.constructor(ReportApplicationOptionForAdminVo.class,
                        reportApplicationOption.id,
                        reportApplicationOption.price,
                        reportApplicationOption.discountPrice,
                        reportApplicationOption.title,
                        reportApplicationOption.code
                ))
                .from(reportApplicationOption)
                .leftJoin(reportApplicationOption.reportApplication, reportApplication)
                .leftJoin(reportApplication.report, report)
                .where(
                        eqReportId(reportId),
                        eqApplicationId(applicationId),
                        eqReportType(reportType),
                        eqPriceType(priceType),
                        containOptionCode(code)
                )
                .orderBy(reportApplication.id.desc())
                .fetch();
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
                        reportFeedbackApplication.reportFeedbackStatus,
                        reportApplication.reportUrl,
                        reportApplication.applyUrl,
                        reportApplication.recruitmentUrl,
                        reportFeedbackApplication.zoomLink,
                        reportFeedbackApplication.zoomPassword,
                        reportFeedbackApplication.desiredDate1,
                        reportFeedbackApplication.desiredDate2,
                        reportFeedbackApplication.desiredDate3,
                        reportApplication.createDate,
                        getConfirmedTimeFor()
                ))
                .from(report)
                .leftJoin(report.applicationList, reportApplication)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .leftJoin(reportApplication.user, user)
                .where(
                        eqReportType(reportType),
                        eqUserId(userId),
                        eqIsCanceled(false)
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
    public Optional<ReportDetailVo> findReportDetailByReportTypeVoForVisible(ReportType reportType) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportDetailVo.class,
                        report.id,
                        report.title,
                        report.notice,
                        report.contents,
                        report.type))
                .from(report)
                .where(
                        eqReportType(reportType),
                        isVisible()
                )
                .orderBy(report.id.desc())
                .fetchFirst());
    }

    @Override
    public Optional<ReportApplicationVo> findReportApplicationVoByApplicationId(Long applicationId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportApplicationVo.class,
                        reportApplication.id,
                        reportFeedbackApplication.id,
                        report.title,
                        reportApplication.reportPriceType,
                        reportApplication.status,
                        reportFeedbackApplication.reportFeedbackStatus,
                        getConfirmedTimeFor(),
                        Expressions.constant(subQueryReportApplicationOptionsTitle(applicationId)),
                        reportApplication.isCanceled
                ))
                .from(reportApplication)
                .leftJoin(reportApplication.report, report)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchOne()
        );
    }

    @Override
    public Optional<ReportPaymentVo> findReportPaymentVoByApplicationId(Long applicationId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(ReportPaymentVo.class,
                        payment.id,
                        payment.finalPrice,
                        coupon.discount,
                        payment.programPrice,
                        payment.programDiscount,
                        reportApplication.refundPrice,
                        reportFeedbackApplication.refundPrice,
                        Expressions.constant(subQueryForReportApplicationPriceInfo(applicationId)),
                        Expressions.constant(subQueryReportApplicationOptionInfos(applicationId)),
                        Expressions.constant(subQueryFeedbackApplicationPriceInfo(applicationId)),
                        reportApplication.createDate,
                        reportApplication.lastModifiedDate
                ))
                .from(reportApplication)
                .leftJoin(reportApplication.reportFeedbackApplication, reportFeedbackApplication)
                .leftJoin(reportApplication.payment, payment)
                .leftJoin(payment.coupon, coupon)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchOne()
        );
    }

    @Override
    public ReportPrice findReportPriceByReportIdAndType(Long reportId, ReportPriceType reportPriceType) {
        return queryFactory
                .selectFrom(reportPrice)
                .leftJoin(reportPrice.report, report)
                .where(
                        eqReportId(reportId),
                        eqReportPriceType(reportPriceType)
                )
                .fetchOne();
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
                        eqReportId(reportId),
                        reportPrice.id.isNotNull()
                )
                .fetch();
    }

    private ReportPriceVo subQueryForReportApplicationPriceInfo(Long applicationId) {
        return queryFactory.select(Projections.constructor(ReportPriceVo.class,
                        reportApplication.reportPriceType,
                        reportApplication.price,
                        reportApplication.discountPrice
                ))
                .from(reportApplication)
                .where(
                        eqApplicationId(applicationId)
                )
                .fetchOne();
    }

    private List<ReportOptionForAdminVo> subQueryReportOptionInfosForAdmin(Long reportId) {
        return queryFactory.select(Projections.constructor(ReportOptionForAdminVo.class,
                        reportOption.id,
                        reportOption.price,
                        reportOption.discountPrice,
                        reportOption.title,
                        reportOption.code
                ))
                .from(report)
                .leftJoin(report.optionList, reportOption)
                .where(
                        eqReportId(reportId),
                        reportOption.id.isNotNull()
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
                        eqReportId(reportId),
                        reportOption.id.isNotNull()
                )
                .fetch();
    }

    private List<ReportOptionVo> subQueryReportApplicationOptionInfos(Long applicationId) {
        return queryFactory.select(Projections.constructor(ReportOptionVo.class,
                        reportApplicationOption.id,
                        reportApplicationOption.price,
                        reportApplicationOption.discountPrice,
                        reportApplicationOption.title
                ))
                .from(reportApplicationOption)
                .leftJoin(reportApplicationOption.reportApplication, reportApplication)
                .where(
                        eqApplicationId(applicationId),
                        reportApplicationOption.id.isNotNull()
                )
                .fetch();
    }

    private FeedbackPriceVo subQueryFeedbackPriceInfo(Long reportId) {
        return queryFactory.select(Projections.constructor(FeedbackPriceVo.class,
                        reportFeedback.id,
                        reportFeedback.reportPriceType,
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

    private FeedbackPriceVo subQueryFeedbackApplicationPriceInfo(Long applicationId) {
        return queryFactory.select(Projections.constructor(FeedbackPriceVo.class,
                        reportFeedbackApplication.id,
                        reportFeedbackApplication.reportPriceType,
                        reportFeedbackApplication.price,
                        reportFeedbackApplication.discountPrice
                ))
                .from(reportFeedbackApplication)
                .leftJoin(reportFeedbackApplication.reportApplication, reportApplication)
                .where(
                        eqApplicationId(applicationId)
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
                        eqReportId(reportId),
                        reportOption.id.isNotNull()
                )
                .fetch();
    }

    private List<String> subQueryReportApplicationOptionsTitle(Long applicationId) {
        return queryFactory.select(Projections.constructor(String.class,
                        reportApplicationOption.title
                ))
                .from(reportApplicationOption)
                .leftJoin(reportApplicationOption.reportApplication, reportApplication)
                .where(
                        eqApplicationId(applicationId),
                        reportApplicationOption.id.isNotNull()
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

    private BooleanExpression eqPriceType(ReportPriceType priceType) {
        return priceType != null ? reportApplication.reportPriceType.eq(priceType) : null;
    }

    private BooleanExpression eqReportPriceType(ReportPriceType priceType) {
        return priceType != null ? reportPrice.reportPriceType.eq(priceType) : null;
    }

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? reportApplication.isCanceled.eq(isCanceled) : null;
    }

    private BooleanExpression containOptionCode(String code) {
        return code != null ? reportApplicationOption.code.contains(code) : null;
    }

    private BooleanExpression isVisible() {
        return report.visibleDate.isNotNull().and(report.visibleDate.before(LocalDateTime.now()));
    }

    private BooleanExpression isApplyFeedback(Boolean isApplyFeedback) {
        return isApplyFeedback != null ? reportFeedbackApplication.isNotNull() : null;
    }
}
