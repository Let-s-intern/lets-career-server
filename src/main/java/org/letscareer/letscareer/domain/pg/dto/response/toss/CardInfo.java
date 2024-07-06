package org.letscareer.letscareer.domain.pg.dto.response.toss;

import jakarta.annotation.Nullable;

public record CardInfo(
        Integer amount,
        String issuerCode,
        @Nullable String acquirerCode,
        String number,
        Integer installmentPlanMonths,
        String approveNo,
        Boolean useCardPoint,
        String cardType,
        String ownerType,
        String acquireStatus,
        Boolean isInterestFree,
        @Nullable String interestPayer
) {
}
