package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetReportDetailResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportDetailService;
import org.letscareer.letscareer.domain.report.vo.ReportDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportDetailServiceImpl implements GetReportDetailService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportDetailResponseDto execute(Long reportId) {
        ReportDetailVo vo = reportHelper.findReportDetailVoOrThrow(reportId);
        return reportMapper.toGetReportDetailResponseDto(vo);
    }
}
