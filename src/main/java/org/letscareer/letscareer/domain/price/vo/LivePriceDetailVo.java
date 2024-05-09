package org.letscareer.letscareer.domain.price.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.price.type.AccountType;
import org.letscareer.letscareer.domain.price.type.LivePriceType;

import java.time.LocalDateTime;

@Builder
public record LivePriceDetailVo(
        Integer price,
        Integer discount,
        String accountNumber,
        LocalDateTime deadline,
        AccountType accountType,
        LivePriceType livePriceType
) {
}
