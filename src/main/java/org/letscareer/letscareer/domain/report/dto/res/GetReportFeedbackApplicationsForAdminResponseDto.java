package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportFeedbackApplicationsForAdminResponseDto(
        List<ReportFeedbackApplicationForAdminVo> reportFeedbackApplicationForAdminInfos,
        PageInfo pageInfo
) {
    public static GetReportFeedbackApplicationsForAdminResponseDto of(List<ReportFeedbackApplicationForAdminVo> reportFeedbackApplicationForAdminInfos,
                                                                      PageInfo pageInfo) {
        return GetReportFeedbackApplicationsForAdminResponseDto.builder()
                .reportFeedbackApplicationForAdminInfos(reportFeedbackApplicationForAdminInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
