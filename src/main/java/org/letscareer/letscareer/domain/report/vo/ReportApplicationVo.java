package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.util.List;

public record ReportApplicationVo(
        Long reportApplicationId,
        Long reportFeedbackApplicationId,
        String title,
        ReportPriceType reportPriceType,
        List<String> options
) {
}
