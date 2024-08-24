package org.letscareer.letscareer.domain.report.mapper;

import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationPaymentForAdminVo;
import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.dto.res.*;
import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.letscareer.letscareer.domain.report.vo.ReportForAdminVo;
import org.letscareer.letscareer.global.common.entity.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {
    public GetReportsForAdminResponseDto toGetReportsForAdminResponseDto(Page<ReportForAdminVo> vos) {
        PageInfo pageInfo = PageInfo.of(vos);
        return GetReportsForAdminResponseDto.of(vos.getContent(), pageInfo);
    }

    public GetReportDetailForAdminResponseDto toGetReportDetailForAdminResponseDto(ReportDetailForAdminVo vo) {
        return GetReportDetailForAdminResponseDto.of(vo);
    }

    public GetReportApplicationsForAdminResponseDto toGetReportApplicationsForAdminResponseDto(Page<ReportApplicationForAdminVo> vos) {
        PageInfo pageInfo = PageInfo.of(vos);
        return GetReportApplicationsForAdminResponseDto.of(vos.getContent(), pageInfo);
    }

    public GetReportFeedbackApplicationsForAdminResponseDto toGetReportFeedbackApplicationsForAdminResponseDto(Page<ReportFeedbackApplicationForAdminVo> vos) {
        PageInfo pageInfo = PageInfo.of(vos);
        return GetReportFeedbackApplicationsForAdminResponseDto.of(vos.getContent(), pageInfo);
    }

    public GetReportApplicationPaymentForAdminResponseDto toGetReportApplicationPaymentForAdminResponseDto(ReportApplicationPaymentForAdminVo vo) {
        return GetReportApplicationPaymentForAdminResponseDto.of(vo);
    }
}
