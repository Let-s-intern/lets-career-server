package org.letscareer.letscareer.domain.payment.vo;

import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record PaymentProgramVo(
        Long paymentId,
        Long applicationId,
        Long programId,
        String programType,
        String title,
        String thumbnail,
        ReportType reportType,
        Integer price,
        Integer finalPrice,
        Integer optionPrice,
        String paymentKey,
        Boolean isCanceled,
        Boolean isRefunded,
        LocalDateTime createDate
) {
}
