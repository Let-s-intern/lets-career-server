package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetMyReportResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetMyReportService;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.MyReportVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetMyReportServiceImpl implements GetMyReportService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetMyReportResponseDto execute(User user, ReportType reportType, Pageable pageable) {
        Page<MyReportVo> vos = reportHelper.findMyReportVos(user, reportType, pageable);
        return reportMapper.toGetMyReportResponseDto(vos);
    }
}
