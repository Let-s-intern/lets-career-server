package org.letscareer.letscareer.domain.report.mapper;

import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.faq.vo.FaqDetailVo;
import org.letscareer.letscareer.domain.pg.dto.response.TossPaymentsResponseDto;
import org.letscareer.letscareer.domain.report.dto.res.*;
import org.letscareer.letscareer.domain.report.vo.*;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportMapper {
    public GetReportPaymentResponseDto toGetReportPaymentResponseDto(ReportApplicationVo reportApplicationInfo,
                                                                     ReportPaymentVo reportPaymentInfo,
                                                                     TossPaymentsResponseDto tossInfo) {
        return GetReportPaymentResponseDto.of(reportApplicationInfo, reportPaymentInfo, tossInfo);
    }

    public GetReportsForAdminResponseDto toGetReportsForAdminResponseDto(Page<ReportForAdminVo> vos) {
        PageInfo pageInfo = PageInfo.of(vos);
        return GetReportsForAdminResponseDto.of(vos.getContent(), pageInfo);
    }

    public GetReportDetailForAdminResponseDto toGetReportDetailForAdminResponseDto(ReportDetailForAdminVo vo,
                                                                                   List<FaqDetailVo> faqInfo) {
        return GetReportDetailForAdminResponseDto.of(vo, faqInfo);
    }

    public GetReportApplicationsForAdminResponseDto toGetReportApplicationsForAdminResponseDto(Page<ReportApplicationForAdminVo> vos) {
        PageInfo pageInfo = PageInfo.of(vos);
        return GetReportApplicationsForAdminResponseDto.of(vos.getContent(), pageInfo);
    }

    public GetReportApplicationOptionsForAdminResponseDto toGetReportApplicationPaymentForAdminResponseDto(List<ReportApplicationOptionForAdminVo> vos) {
        return GetReportApplicationOptionsForAdminResponseDto.of(vos);
    }

    public GetReportDetailResponseDto toGetReportDetailResponseDto(ReportDetailVo vo) {
        return GetReportDetailResponseDto.of(vo);
    }

    public GetReportPriceDetailResponseDto toGetReportPriceDetailResponseDto(ReportPriceDetailVo vo) {
        return GetReportPriceDetailResponseDto.of(vo);
    }

    public GetMyReportResponseDto toGetMyReportResponseDto(Page<MyReportVo> vos) {
        PageInfo pageInfo = PageInfo.of(vos);
        return GetMyReportResponseDto.of(vos.getContent(), pageInfo);
    }

    public GetReportThumbnailResponseDto toGetReportThumbnailResponseDto(List<ReportDetailVo> resumeInfoList,
                                                                         List<ReportDetailVo> personalStatementInfoList,
                                                                         List<ReportDetailVo> portfolioInfoList) {
        return GetReportThumbnailResponseDto.of(resumeInfoList, personalStatementInfoList, portfolioInfoList);
    }

    public CreateReportApplicationResponseDto toCreateReportApplicationResponseDto(TossPaymentsResponseDto tossInfo) {
        return CreateReportApplicationResponseDto.of(tossInfo);
    }

    public GetReportTitleResponseDto toGetReportTitleResponseDto(ReportTitleVo reportTitleVo) {
        return GetReportTitleResponseDto.of(reportTitleVo);
    }
}
