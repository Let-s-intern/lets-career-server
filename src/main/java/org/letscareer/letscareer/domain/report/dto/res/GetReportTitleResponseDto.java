package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.ReportTitleVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportTitleResponseDto(
        String title
) {
    public static GetReportTitleResponseDto of(ReportTitleVo titleVo) {
        return GetReportTitleResponseDto.builder()
                .title(titleVo.title())
                .build();
    }
}
