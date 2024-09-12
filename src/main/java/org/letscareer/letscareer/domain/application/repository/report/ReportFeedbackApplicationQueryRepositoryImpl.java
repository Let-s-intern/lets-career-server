package org.letscareer.letscareer.domain.application.repository.report;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.report.QReportFeedbackApplication.reportFeedbackApplication;

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

    private BooleanExpression isFeedbackDate() {
        LocalDate now = LocalDate.now();
        return reportFeedbackApplication.feedbackDate.isNotNull().and(Expressions.dateTemplate(LocalDate.class, "DATE_FORMAT({0}, '%Y-%m-%d')", reportFeedbackApplication.feedbackDate).eq(now));
    }

    private BooleanExpression eqIsCanceled(Boolean isCanceled) {
        return isCanceled != null ? reportFeedbackApplication.isCanceled.eq(isCanceled) : null;
    }
}
