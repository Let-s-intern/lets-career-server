package org.letscareer.letscareer.domain.report.vo;

public record ReportOptionVo(
        Long reportOptionId,
        String optionTitle,
        Integer price,
        Integer discountPrice,
        String title
) {
}
