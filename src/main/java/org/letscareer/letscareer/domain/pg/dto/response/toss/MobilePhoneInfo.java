package org.letscareer.letscareer.domain.pg.dto.response.toss;

public record MobilePhoneInfo(
        String customerMobilePhone,
        String settlementStatus,
        String receiptUrl
) {
}
