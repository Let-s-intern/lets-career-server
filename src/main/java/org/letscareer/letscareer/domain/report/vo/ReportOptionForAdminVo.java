package org.letscareer.letscareer.domain.report.vo;

public record ReportOptionForAdminVo(
        Long reportOptionId,
        Integer price,
        Integer discountPrice,
        String title,
        String code
) {
}
