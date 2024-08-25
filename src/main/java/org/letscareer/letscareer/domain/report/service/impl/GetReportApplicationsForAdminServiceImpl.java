package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.vo.ReportApplicationForAdminVo;
import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportApplicationsForAdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportApplicationsForAdminServiceImpl implements GetReportApplicationsForAdminService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportApplicationsForAdminResponseDto execute(Long reportId, Pageable pageable) {
        Page<ReportApplicationForAdminVo> vos = reportHelper.findReportApplicationForAdminVos(reportId, pageable);
        return reportMapper.toGetReportApplicationsForAdminResponseDto(vos);
    }
}
