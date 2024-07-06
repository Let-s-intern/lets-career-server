package org.letscareer.letscareer.domain.pg.dto.response.toss;

import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public record CancelsInfo(
        Integer cancelAmount,
        String cancelReason,
        Integer taxFreeAmount,
        Integer taxExemptionAmount,
        Integer refundableAmount,
        Integer easyPayDiscountAmount,
        LocalDateTime canceledAt,
        String transactionKey,
        @Nullable String receiptKey,
        String cancelStatus,
        @Nullable String cancelRequestId
) {
}
