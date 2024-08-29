package org.letscareer.letscareer.domain.report.vo;

import java.util.List;

public record ReportPriceDetailVo(
        Long reportId,
        List<ReportPriceVo> reportPriceInfos,
        List<ReportOptionVo> reportOptionInfos,
        FeedbackPriceVo feedbackPriceInfo
) {
}
