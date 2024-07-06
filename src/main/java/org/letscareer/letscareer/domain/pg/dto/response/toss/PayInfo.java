package org.letscareer.letscareer.domain.pg.dto.response.toss;

public record PayInfo(
        String provider,
        Integer amount,
        String discountAmount
) {
}
