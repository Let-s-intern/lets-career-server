package org.letscareer.letscareer.domain.pg.dto.response.toss;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record VirtualAccountInfo(
        String accountType,
        String accountNumber,
        String bankCode,
        String customerName,
        String dueDate,
        String refundStatus,
        Boolean expired,
        String settlementStatus,
        String refundReceiveAccount
) {
}
