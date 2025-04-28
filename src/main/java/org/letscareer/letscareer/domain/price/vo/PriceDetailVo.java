package org.letscareer.letscareer.domain.price.vo;

import lombok.Builder;

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
