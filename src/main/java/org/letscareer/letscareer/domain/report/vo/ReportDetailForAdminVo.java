package org.letscareer.letscareer.domain.report.vo;

import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;
import java.util.List;

public record ReportDetailForAdminVo(
        Long reportId,
        ReportType type,
        String title,
        String contents,
        String notice,
        List<ReportPriceVo> reportPriceInfos,
        List<ReportOptionForAdminVo> reportOptionForAdminInfos,
        FeedbackPriceVo feedbackPriceInfo,
        Boolean isVisible,
        LocalDateTime visibleDate
) {
}
