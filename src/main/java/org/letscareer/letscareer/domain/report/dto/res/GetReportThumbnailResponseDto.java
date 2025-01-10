package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.ReportDetailVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportThumbnailResponseDto(
        List<ReportDetailVo> resumeInfoList,
        List<ReportDetailVo> personalStatementInfoList,
        List<ReportDetailVo> portfolioInfoList
) {
    public static GetReportThumbnailResponseDto of(List<ReportDetailVo> resumeInfoList,
                                                   List<ReportDetailVo> personalStatementInfoList,
                                                   List<ReportDetailVo> portfolioInfoList) {
        return GetReportThumbnailResponseDto.builder()
                .resumeInfoList(resumeInfoList)
                .personalStatementInfoList(personalStatementInfoList)
                .portfolioInfoList(portfolioInfoList)
                .build();
    }
}
