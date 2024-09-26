package org.letscareer.letscareer.domain.payment.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentDetailVo(
        Long id,
        Integer finalPrice,
        Integer couponDiscount,
        Boolean isRefunded,
        LocalDateTime createDate,
        LocalDateTime lastModifiedDate
) {
}
