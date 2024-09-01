package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.FeedbackPriceVo;
import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.letscareer.letscareer.domain.report.vo.ReportOptionVo;
import org.letscareer.letscareer.domain.report.vo.ReportPriceVo;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record GetReportDetailForAdminResponseDto(
        Long reportId,
        ReportType reportType,
        String title,
        String contents,
        String notice,
        List<ReportPriceVo> reportPriceInfos,
        List<ReportOptionVo> reportOptionInfos,
        FeedbackPriceVo feedbackPriceInfo,
        LocalDateTime visibleDate
) {
    public static GetReportDetailForAdminResponseDto of(ReportDetailForAdminVo vo) {
        return GetReportDetailForAdminResponseDto.builder()
                .reportId(vo.reportId())
                .reportType(vo.type())
                .title(vo.title())
                .contents(vo.contents())
                .notice(vo.notice())
                .reportPriceInfos(vo.reportPriceInfos())
                .reportOptionInfos(vo.reportOptionInfos())
                .feedbackPriceInfo(vo.feedbackPriceInfo())
                .visibleDate(vo.visibleDate())
                .build();
    }
}
