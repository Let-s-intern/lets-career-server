package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetReportThumbnailResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetReportThumbnailService;
import org.letscareer.letscareer.domain.report.vo.ReportDetailVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.letscareer.letscareer.domain.report.type.ReportType.*;

@RequiredArgsConstructor
@Transactional
@Service
public class GetReportThumbnailServiceImpl implements GetReportThumbnailService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetReportThumbnailResponseDto execute() {
        List<ReportDetailVo> resumeInfoList = reportHelper.findAllReportDetailByReportTypeVoForVisibleOrNull(RESUME);
        List<ReportDetailVo> personalStatementInfoList = reportHelper.findAllReportDetailByReportTypeVoForVisibleOrNull(PERSONAL_STATEMENT);
        List<ReportDetailVo> portfolioInfoList = reportHelper.findAllReportDetailByReportTypeVoForVisibleOrNull(PORTFOLIO);
        return reportMapper.toGetReportThumbnailResponseDto(resumeInfoList, personalStatementInfoList, portfolioInfoList);
    }
}
