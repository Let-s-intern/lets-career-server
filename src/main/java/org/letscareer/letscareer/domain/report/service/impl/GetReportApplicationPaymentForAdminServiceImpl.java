package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationPaymentForAdminVo;
import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationPaymentForAdminResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportApplicationPaymentForAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportApplicationPaymentForAdminServiceImpl implements GetReportApplicationPaymentForAdminService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportApplicationPaymentForAdminResponseDto execute(Long reportId, Long applicationId) {
        ReportApplicationPaymentForAdminVo vo = reportHelper.findReportApplicationPaymentForAdminVoOrThrow(reportId, applicationId);
        return reportMapper.toGetReportApplicationPaymentForAdminResponseDto(vo);
    }
}
