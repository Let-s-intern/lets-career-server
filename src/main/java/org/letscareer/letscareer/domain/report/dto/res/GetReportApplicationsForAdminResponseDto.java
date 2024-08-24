package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportApplicationsForAdminResponseDto(
        List<ReportApplicationForAdminVo> reportApplicationsForAdminInfos,
        PageInfo pageInfo
) {
    public static GetReportApplicationsForAdminResponseDto of(List<ReportApplicationForAdminVo> reportApplicationsForAdminInfos,
                                                              PageInfo pageInfo) {
        return GetReportApplicationsForAdminResponseDto.builder()
                .reportApplicationsForAdminInfos(reportApplicationsForAdminInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
