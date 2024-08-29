package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.ReportDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportThumbnailResponseDto(
        ReportDetailVo resumeInfo,
        ReportDetailVo personalStatementInfo,
        ReportDetailVo portfolioInfo
) {
    public static GetReportThumbnailResponseDto of(ReportDetailVo resumeInfo,
                                                   ReportDetailVo personalStatementInfo,
                                                   ReportDetailVo portfolioInfo) {
        return GetReportThumbnailResponseDto.builder()
                .resumeInfo(resumeInfo)
                .personalStatementInfo(personalStatementInfo)
                .portfolioInfo(portfolioInfo)
                .build();
    }
}
