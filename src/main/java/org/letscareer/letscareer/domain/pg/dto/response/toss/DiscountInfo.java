package org.letscareer.letscareer.domain.pg.dto.response.toss;

import lombok.Builder;

@Builder
public record DiscountInfo(
        String amount
) {
}
