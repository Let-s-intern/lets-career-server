package org.letscareer.letscareer.domain.pg.dto.response.toss;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CancelsInfo(
        Integer cancelAmount,
        String cancelReason,
        Integer taxFreeAmount,
        Integer taxExemptionAmount,
        Integer refundableAmount,
        Integer easyPayDiscountAmount,
        String canceledAt,
        String transactionKey,
        @Nullable String receiptKey,
        String cancelStatus,
        @Nullable String cancelRequestId
) {
}
