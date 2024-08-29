package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.ReportDetailVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportDetailResponseDto(
        Long reportId,
        String title,
        String notice,
        String contents,
        ReportType reportType
) {
    public static GetReportDetailResponseDto of(ReportDetailVo vo) {
        return GetReportDetailResponseDto.builder()
                .reportId(vo.reportId())
                .title(vo.title())
                .notice(vo.notice())
                .contents(vo.contents())
                .reportType(vo.reportType())
                .build();
    }
}
