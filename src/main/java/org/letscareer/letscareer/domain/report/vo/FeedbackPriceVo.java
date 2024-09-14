package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportPriceType;

public record FeedbackPriceVo(
        Long reportFeedbackId,
        ReportPriceType reportPriceType,
        Integer feedbackPrice,
        Integer feedbackDiscountPrice
) {
}
