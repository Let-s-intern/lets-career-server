package org.letscareer.letscareer.domain.report.vo;

import java.time.LocalDateTime;
import java.util.List;

public record ReportPaymentVo(
        Long paymentId,
        Integer finalPrice,
        Integer couponDiscount,
        Integer programPrice,
        Integer programDiscount,
        Integer reportRefundPrice,
        Integer feedbackRefundPrice,
        ReportPriceVo reportPriceInfo,
        List<ReportOptionVo> reportOptionInfos,
        FeedbackPriceVo feedbackPriceInfo,
        Boolean isRefunded,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
