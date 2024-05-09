package org.letscareer.letscareer.domain.price.dto.request;

import org.letscareer.letscareer.domain.price.type.AccountType;

import java.time.LocalDateTime;

public record CreatePriceRequestDto(
        Integer price,
        Integer discount,
        String accountNumber,
        LocalDateTime deadline,
        AccountType accountType
) {
}
