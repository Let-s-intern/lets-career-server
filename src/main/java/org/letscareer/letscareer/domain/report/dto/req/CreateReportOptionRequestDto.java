package org.letscareer.letscareer.domain.report.dto.req;

public record CreateReportOptionRequestDto(
        Integer price,
        Integer discountPrice,
        String title,
        String code
) {
}
