package org.letscareer.letscareer.domain.report.vo;

import lombok.Builder;

@Builder
public record ReportApplicationOptionPriceVo(
        Integer price,
        Integer discountPrice
) {
}
