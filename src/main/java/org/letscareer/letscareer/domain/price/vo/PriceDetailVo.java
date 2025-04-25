package org.letscareer.letscareer.domain.price.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.price.type.AccountType;

import java.time.LocalDateTime;

@Builder
public record PriceDetailVo(
        Long id,
        Integer price,
        Integer discount,
        Integer refund,
        Integer option,
        Integer optionDiscount
) {

}
