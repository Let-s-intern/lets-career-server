package org.letscareer.letscareer.domain.application.repository.report;

import java.util.List;

public interface ReportApplicationQueryRepository {
    List<Long> findAllIngNotificationReportApplicationId();
    List<Long> findAllRemindNotificationReportApplicationId();
    List<Long> findAllReviewNotificationReportApplicationId();
    List<Long> findAllAutoRefundNotificationReportApplicationId();
}
