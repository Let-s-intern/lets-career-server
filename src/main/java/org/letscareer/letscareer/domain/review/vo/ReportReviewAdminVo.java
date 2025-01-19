package org.letscareer.letscareer.domain.review.vo;

import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record ReportReviewAdminVo(
        Long reviewId,
        LocalDateTime createDate,
        ReportType reportType,
        String title,
        String name,
        Integer score,
        Integer npsScore,
        String goodPoint,
        String badPoint,
        Boolean isVisible
) {
}
