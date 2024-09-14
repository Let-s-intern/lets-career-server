package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportApplicationOptionsForAdminResponseDto(
        List<ReportApplicationOptionForAdminVo> reportApplicationOptionForAdminInfos
) {
    public static GetReportApplicationOptionsForAdminResponseDto of(List<ReportApplicationOptionForAdminVo> vos) {
        return GetReportApplicationOptionsForAdminResponseDto.builder()
                .reportApplicationOptionForAdminInfos(vos)
                .build();
    }
}
