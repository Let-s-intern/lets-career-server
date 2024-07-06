package org.letscareer.letscareer.domain.pg.dto.response.toss;

public record TransferInfo(
        String bankCode,
        String settlementStatus
) {
}
