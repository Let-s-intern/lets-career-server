package org.letscareer.letscareer.domain.payment.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.user.type.AccountType;

import java.time.LocalDateTime;

@Builder
public record PaymentDetailVo(
        Long id,
        Integer finalPrice,
        AccountType accountType,
        String accountNum,
        LocalDateTime deadline,
        Integer price,
        Integer discount,
        Integer couponDiscount
) {
}
