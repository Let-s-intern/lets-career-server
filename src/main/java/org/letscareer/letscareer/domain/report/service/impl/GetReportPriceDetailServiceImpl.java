package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetReportPriceDetailResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportPriceDetailService;
import org.letscareer.letscareer.domain.report.vo.ReportPriceDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportPriceDetailServiceImpl implements GetReportPriceDetailService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportPriceDetailResponseDto execute(Long reportId) {
        ReportPriceDetailVo vo = reportHelper.findReportPriceDetailVoOrThrow(reportId);
        return reportMapper.toGetReportPriceDetailResponseDto(vo);
    }
}
