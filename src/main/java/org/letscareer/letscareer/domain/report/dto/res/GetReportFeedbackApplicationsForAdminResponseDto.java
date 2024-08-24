package org.letscareer.letscareer.domain.report.dto.res;

import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

public record GetReportFeedbackApplicationsForAdminResponseDto(
        List<ReportFeedbackApplicationForAdminVo> reportFeedbackApplicationForAdminInfos,
        PageInfo pageInfo
) {
}
