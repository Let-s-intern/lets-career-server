package org.letscareer.letscareer.domain.report.dto.res;

import lombok.AccessLevel;
import lombok.Builder;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.*;

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
        List<ReportOptionForAdminVo> reportOptionForAdminInfos,
        FeedbackPriceVo feedbackPriceInfo,
        List<FaqDetailVo> faqInfo,
        LocalDateTime visibleDate
) {
    public static GetReportDetailForAdminResponseDto of(ReportDetailForAdminVo vo,
                                                        List<FaqDetailVo> faqInfo) {
        return GetReportDetailForAdminResponseDto.builder()
                .reportId(vo.reportId())
                .reportType(vo.type())
                .title(vo.title())
                .contents(vo.contents())
                .notice(vo.notice())
                .reportPriceInfos(vo.reportPriceInfos())
                .reportOptionForAdminInfos(vo.reportOptionForAdminInfos())
                .feedbackPriceInfo(vo.feedbackPriceInfo())
                .faqInfo(faqInfo)
                .visibleDate(vo.visibleDate())
                .build();
    }
}
