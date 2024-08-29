package org.letscareer.letscareer.domain.application.vo;

public record ReportApplicationOptionForAdminVo(
        Long reportApplicationOptionId,
        Integer price,
        Integer discountPrice,
        String title,
        String code
) {
}
