package org.letscareer.letscareer.domain.pg.dto.response.toss;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.util.List;

@Builder
public record CardInterestFreeInfo(
        @Nullable String issuerCode,
        Integer minimumPaymentAmount,
        String dueDate,
        List<Integer> installmentFreeMonths
) {
}
