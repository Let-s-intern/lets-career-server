package org.letscareer.letscareer.domain.payment.vo;

import java.time.LocalDateTime;

public record PaymentProgramVo(
        Long paymentId,
        Long applicationId,
        String programType,
        String title,
        String thumbnail,
        Integer price,
        Integer finalPrice,
        String paymentKey,
        Boolean isCanceled,
        Boolean isRefunded,
        LocalDateTime createDate
) {
}
