package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetReportTitleResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportTitleService;
import org.letscareer.letscareer.domain.report.vo.ReportTitleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportTitleServiceImpl implements GetReportTitleService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportTitleResponseDto getReportTitle(Long reportId) {
        ReportTitleVo reportTitleVo = reportHelper.findReportTitleVoOrThrow(reportId);
        return reportMapper.toGetReportTitleResponseDto(reportTitleVo);
    }
}
