package org.letscareer.letscareer.domain.application.repository.report;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;
import static org.letscareer.letscareer.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReportApplicationQueryRepositoryImpl implements ReportApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findAllIngNotificationReportApplicationId() {
        return queryFactory
                .select(reportApplication.id)
                .from(reportApplication)
                .where(
                        eqIsCanceled(false),
                        eqStatus(ReportApplicationStatus.APPLIED),
                        applyUrlIsNotNull(),
                        isAfter3Hours()
                )
                .fetch();
    }

    @Override
    public List<Long> findAllRemindNotificationReportApplicationId() {
        return queryFactory
                .select(reportApplication.id)
                .from(reportApplication)
                .where(
                        eqIsCanceled(false),
                        eqStatus(ReportApplicationStatus.APPLIED),
                        applyUrlIsNull(),
                        isAfter3Days()
                )
                .fetch();
    }

    @Override
    public List<Long> findAllLastRemindNotificationReportApplicationId() {
        return queryFactory
                .select(reportApplication.id)
                .from(reportApplication)
                .where(
                        eqIsCanceled(false),
                        eqStatus(ReportApplicationStatus.APPLIED),
                        applyUrlIsNull(),
                        isBefore12Hours()
                )
                .fetch();
    }

    @Override
    public List<Long> findAllAutoRefundNotificationReportApplicationId() {
        return queryFactory
                .select(reportApplication.id)
                .from(reportApplication)
                .where(
                        eqIsCanceled(false),
                        eqStatus(ReportApplicationStatus.APPLIED),
                        applyUrlIsNull(),
                        isAfter7Days()
                )
                .fetch();
    }

    @Override
    public List<Long> findAllReviewNotificationReportApplicationId() {
        return queryFactory
                .select(reportApplication.id)
                .from(reportApplication)
                .where(
                        eqStatus(ReportApplicationStatus.COMPLETED), // 진단완료 상태
                        eqIsCanceled(false),                        // 신청 취소하지 않음
                        betweenReportUrlDate(LocalDateTime.now()),  // 전날 10시 ~ 당일 10시
                        isNoReviewForApplication()                  // 리뷰 작성하지 않음
                )
                .fetch();
    }

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? reportApplication.isCanceled.eq(isCanceled) : null;
    }

    private BooleanExpression eqStatus(ReportApplicationStatus status) {
        return status != null ? reportApplication.status.eq(status) : null;
    }

    private BooleanExpression isAfter3Hours() {
        LocalDateTime now = LocalDateTime.now();
        return reportApplication.applyUrlDate.isNotNull().and(reportApplication.applyUrlDate.before(now.minusHours(3L)));
    }

    private BooleanExpression applyUrlIsNull() {
        return reportApplication.applyUrl.isNull();
    }

    private BooleanExpression applyUrlIsNotNull() {
        return reportApplication.applyUrl.isNotNull().and(reportApplication.applyUrlDate.isNotNull());
    }

    private BooleanExpression isAfter3Days() {
        // LocalDate nowMinus3Days = LocalDate.now().minusDays(3);
        LocalDate nowMinus3Days = LocalDate.now().minusDays(1);
        return Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, '%Y-%m-%d')", reportApplication.payment.createDate).eq(nowMinus3Days);
    }

    private BooleanExpression isBefore12Hours() {
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
//        LocalDateTime startOfPeriod = now.minusDays(6).minusHours(12);
//        LocalDateTime endOfPeriod = now.minusDays(6).minusHours(11);
        LocalDateTime startOfPeriod = now.minusDays(1).minusHours(12);
        LocalDateTime endOfPeriod = now.minusDays(1).minusHours(11);
        return reportApplication.payment.createDate.goe(startOfPeriod)
                .and(reportApplication.payment.createDate.lt(endOfPeriod));
    }

    private BooleanExpression isAfter7Days() {
        LocalDateTime now = LocalDateTime.now();
//        return reportApplication.payment.createDate.before(now.minusDays(7L));
        return reportApplication.payment.createDate.before(now.minusDays(2L));
    }

    private BooleanExpression betweenReportUrlDate(LocalDateTime now) {
        LocalDateTime startOfPeriod = now.withHour(10).withMinute(0).withSecond(0).minusDays(1); // 전날 10시 0분 0초
        LocalDateTime endOfPeriod = now.withHour(10).withMinute(0).withSecond(0);               // 오늘 10시 0분 0초
        return reportApplication.reportUrlDate.goe(startOfPeriod) // >= 전날 10시 0분 0초
                .and(reportApplication.reportUrlDate.lt(endOfPeriod)); // < 오늘 10시 0분 0초
    }

    private BooleanExpression isNoReviewForApplication() {
        return JPAExpressions
                .selectFrom(review)
                .where(review.application.id.eq(reportApplication.id))
                .notExists();
    }
}
