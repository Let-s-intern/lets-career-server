package org.letscareer.letscareer.domain.pg.dto.response.toss;

import lombok.Builder;

@Builder
public record PayInfo(
        String provider,
        Integer amount,
        String discountAmount
) {
}
