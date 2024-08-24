package org.letscareer.letscareer.domain.report.vo;

public record ReportOptionVo(
        Long reportOptionId,
        Integer price,
        Integer discountPrice,
        String title
) {
}
