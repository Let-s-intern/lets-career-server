package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportFeedbackApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.dto.res.GetReportFeedbackApplicationsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportFeedbackApplicationsForAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportFeedbackApplicationsForAdminServiceImpl implements GetReportFeedbackApplicationsForAdminService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportFeedbackApplicationsForAdminResponseDto execute(Long reportId, Pageable pageable) {
        Page<ReportFeedbackApplicationForAdminVo> vos = reportHelper.findReportFeedbackApplicationForAdminVos(reportId, pageable);
        return reportMapper.toGetReportFeedbackApplicationsForAdminResponseDto(vos);
    }
}
