package org.letscareer.letscareer.domain.report.dto.req;

import org.letscareer.letscareer.domain.report.type.ReportPriceType;

public record CreateReportPriceRequestDto(
        ReportPriceType reportPriceType,
        Integer price,
        Integer discountPrice
) {
}
