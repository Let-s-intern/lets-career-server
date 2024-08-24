package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.vo.FeedbackPriceVo;
import org.letscareer.letscareer.domain.report.vo.ReportOptionVo;
import org.letscareer.letscareer.domain.report.vo.ReportPriceDetailVo;
import org.letscareer.letscareer.domain.report.vo.ReportPriceVo;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportPriceDetailResponseDto(
        Long reportId,
        List<ReportPriceVo> reportPriceInfos,
        List<ReportOptionVo> reportOptionInfos,
        FeedbackPriceVo feedbackPriceInfo
) {
    public static GetReportPriceDetailResponseDto of(ReportPriceDetailVo vo) {
        return GetReportPriceDetailResponseDto.builder()
                .reportId(vo.reportId())
                .reportPriceInfos(vo.reportPriceInfos())
                .reportOptionInfos(vo.reportOptionInfos())
                .feedbackPriceInfo(vo.feedbackPriceInfo())
                .build();
    }
}
