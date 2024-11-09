package org.letscareer.letscareer.domain.pg.dto.response.toss;

import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
public record CardDiscountInfo(
        String companyCode,
        @Nullable String issuerCode,
        Integer discountAmount,
        Integer balance,
        String discountCode,
        String dueDate,
        Integer minimumPaymentAmount,
        Integer maximumPaymentAmount,
        String currency
) {
}
