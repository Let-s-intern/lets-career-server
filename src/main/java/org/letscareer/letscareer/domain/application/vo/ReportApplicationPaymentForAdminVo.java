package org.letscareer.letscareer.domain.application.vo;

import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.util.List;

public record ReportApplicationPaymentForAdminVo(
        Long paymentId,
        String orderId,
        ReportPriceType reportPriceType,
        List<String> options,
        Long feedbackApplicationId,
        String couponTitle,
        Integer finalPrice,
        Boolean isRefunded
) {
}
