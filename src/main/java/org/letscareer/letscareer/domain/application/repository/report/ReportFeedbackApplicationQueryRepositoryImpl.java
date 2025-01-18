package org.letscareer.letscareer.domain.application.repository.report;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.report.QReportFeedbackApplication.reportFeedbackApplication;
import static org.letscareer.letscareer.domain.review.entity.QOldReview.oldReview;
import static org.letscareer.letscareer.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReportFeedbackApplicationQueryRepositoryImpl implements ReportFeedbackApplicationQueryRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<Long> findDdayNotificationReportFeedbackApplicationIds() {
        return queryFactory
                .select(reportFeedbackApplication.id)
                .from(reportFeedbackApplication)
                .where(
                        isFeedbackDate(),
                        eqIsCanceled(false)
                )
                .fetch();
    }

    @Override
    public List<Long> findAllReviewNotificationReportFeedbackApplicationId() {
        return queryFactory
                .select(reportFeedbackApplication.id)
                .from(reportFeedbackApplication)
                .where(
                        eqStatus(ReportFeedbackStatus.CONFIRMED),
                        eqIsCanceled(false),
                        isBefore1Hours(),
                        isNoReviewForApplication()
                )
                .fetch();
    }

    private BooleanExpression isFeedbackDate() {
        LocalDate now = LocalDate.now();
        return reportFeedbackApplication.feedbackDate.isNotNull().and(Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, '%Y-%m-%d')", reportFeedbackApplication.feedbackDate).eq(now));
    }

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? reportFeedbackApplication.isCanceled.eq(isCanceled) : null;
    }

    private BooleanExpression eqStatus(ReportFeedbackStatus status) {
        return status != null ? reportFeedbackApplication.reportFeedbackStatus.eq(status) : null;
    }

    private BooleanExpression isBefore1Hours() {
        LocalDateTime now = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        return reportFeedbackApplication.feedbackDate.eq(now.minusHours(1));
    }

    private BooleanExpression isNoReviewForApplication() {
        return JPAExpressions
                .selectFrom(oldReview)
                .where(oldReview.application.id.eq(reportFeedbackApplication.reportApplication.id))
                .notExists();
    }
}
