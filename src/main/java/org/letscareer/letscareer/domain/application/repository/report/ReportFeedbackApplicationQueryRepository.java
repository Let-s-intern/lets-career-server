package org.letscareer.letscareer.domain.application.repository.report;

import java.util.List;

public interface ReportFeedbackApplicationQueryRepository {
    List<Long> findDdayNotificationReportFeedbackApplicationIds();
    List<Long> findAllReviewNotificationReportFeedbackApplicationId();

}
