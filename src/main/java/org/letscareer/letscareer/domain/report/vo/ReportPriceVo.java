package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportPriceType;

public record ReportPriceVo(
        ReportPriceType reportPriceType,
        Integer price,
        Integer discountPrice
) {
}
