package org.letscareer.letscareer.domain.report.vo;

import java.util.List;

public record ReportPaymentVo(
        Long paymentId,
        Integer finalPrice,
        Integer couponDiscount,
        Integer reportRefundPrice,
        Integer feedbackRefundPrice,
        ReportPriceVo reportPriceInfo,
        List<ReportOptionVo> reportOptionInfos,
        FeedbackPriceVo feedbackPriceInfo
) {
}
