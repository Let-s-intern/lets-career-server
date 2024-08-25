package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetReportDetailForAdminResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportDetailForAdminService;
import org.letscareer.letscareer.domain.report.vo.ReportDetailForAdminVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportDetailForAdminServiceImpl implements GetReportDetailForAdminService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportDetailForAdminResponseDto execute(Long reportId) {
        ReportDetailForAdminVo vo = reportHelper.findReportDetailForAdminVoOrThrow(reportId);
        return reportMapper.toGetReportDetailForAdminResponseDto(vo);
    }
}
