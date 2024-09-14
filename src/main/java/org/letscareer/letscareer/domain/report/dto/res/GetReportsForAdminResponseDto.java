package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportsForAdminResponseDto(
        List<ReportForAdminVo> reportForAdminInfos,
        PageInfo pageInfo
) {
    public static GetReportsForAdminResponseDto of(List<ReportForAdminVo> reportForAdminInfos,
                                                   PageInfo pageInfo) {
        return GetReportsForAdminResponseDto.builder()
                .reportForAdminInfos(reportForAdminInfos)
                .pageInfo(pageInfo)
                .build();
    }
}
