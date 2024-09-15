package org.letscareer.letscareer.domain.application.repository.report;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;

import java.time.LocalDateTime;
import java.util.List;

import static org.letscareer.letscareer.domain.application.entity.report.QReportApplication.reportApplication;

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
                        isAfter3Hours()
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
        return reportApplication.payment.createDate.before(now.minusHours(3L));
    }
}
