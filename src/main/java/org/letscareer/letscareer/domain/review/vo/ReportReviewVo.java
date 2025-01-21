package org.letscareer.letscareer.domain.review.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;

public record ReportReviewVo(
        @JsonIgnore
        Long userId,
        Long reviewId,
        LocalDateTime createDate,
        ReportType reportType,
        String title,
        Integer score,
        Integer npsScore,
        String goodPoint,
        String badPoint
) {
}
