package org.letscareer.letscareer.domain.pg.dto.response.toss;

public record CashReceiptInfo(
        String type,
        String receiptKey,
        String issueNumber,
        String receiptUrl,
        Integer amount,
        Integer taxFreeAmount
) {
}
