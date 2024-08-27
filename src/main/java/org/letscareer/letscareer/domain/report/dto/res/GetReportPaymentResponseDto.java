package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationVo;
import org.letscareer.letscareer.domain.report.vo.ReportPaymentVo;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportPaymentResponseDto(
        ReportApplicationVo reportApplicationInfo,
        ReportPaymentVo reportPaymentInfo,
        TossPaymentsResponseDto tossInfo
) {
    public static GetReportPaymentResponseDto of(ReportApplicationVo reportApplicationInfo,
                                                 ReportPaymentVo reportPaymentInfo,
                                                 TossPaymentsResponseDto tossInfo) {
        return GetReportPaymentResponseDto.builder()
                .reportApplicationInfo(reportApplicationInfo)
                .reportPaymentInfo(reportPaymentInfo)
                .tossInfo(tossInfo)
                .build();
    }
}
