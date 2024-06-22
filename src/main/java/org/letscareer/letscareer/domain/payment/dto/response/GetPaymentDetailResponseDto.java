package org.letscareer.letscareer.domain.payment.dto.response;

import org.letscareer.letscareer.domain.user.type.AccountType;

import java.time.LocalDateTime;

public record GetPaymentDetailResponseDto(
        AccountType accountType,
        String accountNum,
        LocalDateTime deadline,
        Integer price,
        Integer discount,
        Integer couponDiscount,
        Integer finalPrice
) {
}
