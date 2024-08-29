package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationOptionForAdminVo;
import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationOptionsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportApplicationPaymentForAdminService;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportApplicationPaymentForAdminServiceImpl implements GetReportApplicationPaymentForAdminService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportApplicationOptionsForAdminResponseDto execute(Long reportId, Long applicationId, ReportType reportType, ReportPriceType priceType, String code) {
        List<ReportApplicationOptionForAdminVo> vos = reportHelper.findReportApplicationPaymentForAdminVos(reportId, applicationId, reportType, priceType, code);
        return reportMapper.toGetReportApplicationPaymentForAdminResponseDto(vos);
    }
}
