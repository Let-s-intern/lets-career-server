package org.letscareer.letscareer.domain.pg.dto.response.toss;

import java.time.LocalDateTime;

public record VirtualAccountInfo(
        String accountType,
        String accountNumber,
        String bankCode,
        String customerName,
        LocalDateTime dueDate,
        String refundStatus,
        Boolean expired,
        String settlementStatus,
        String refundReceiveAccount
) {
}
