package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetReportThumbnailResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportThumbnailService;
import org.letscareer.letscareer.domain.report.vo.ReportDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.letscareer.letscareer.domain.report.type.ReportType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportThumbnailServiceImpl implements GetReportThumbnailService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportThumbnailResponseDto execute() {
        ReportDetailVo resumeInfo = reportHelper.findReportDetailByReportTypeVoForVisibleOrNull(RESUME);
        ReportDetailVo personalStatementInfo = reportHelper.findReportDetailByReportTypeVoForVisibleOrNull(PERSONAL_STATEMENT);
        ReportDetailVo portfolioInfo = reportHelper.findReportDetailByReportTypeVoForVisibleOrNull(PORTFOLIO);
        return reportMapper.toGetReportThumbnailResponseDto(resumeInfo, personalStatementInfo, portfolioInfo);
    }
}
