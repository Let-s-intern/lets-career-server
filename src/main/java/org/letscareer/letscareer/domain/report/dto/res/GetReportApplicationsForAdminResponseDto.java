package org.letscareer.letscareer.domain.report.dto.res;

import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

public record GetReportApplicationsForAdminResponseDto(
        List<ReportApplicationForAdminVo> reportApplicationsForAdminInfos,
        PageInfo pageInfo
) {
}
