package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.time.LocalDateTime;
import java.util.List;

public record ReportApplicationVo(
        Long reportApplicationId,
        Long reportFeedbackApplicationId,
        String title,
        ReportPriceType reportPriceType,
        ReportApplicationStatus reportApplicationStatus,
        ReportFeedbackStatus reportFeedbackStatus,
        LocalDateTime reportFeedbackDesiredDate,
        LocalDateTime applyUrlDate,
        List<String> options,
        Boolean isCanceled
) {
}
