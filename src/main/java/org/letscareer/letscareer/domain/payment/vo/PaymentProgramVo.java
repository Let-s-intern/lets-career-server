package org.letscareer.letscareer.domain.payment.vo;

import java.time.LocalDateTime;

public record PaymentProgramVo(
        Long paymentId,
        String title,
        String thumbnail,
        Integer price,
        Integer finalPrice,
        String paymentKey,
        Boolean isCanceled,
        LocalDateTime createDate
) {
}
