package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.res.GetMyReportFeedbackResponseDto;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.mapper.ReportMapper;
import org.letscareer.letscareer.domain.report.service.GetMyReportFeedbackService;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.report.vo.MyReportFeedbackVo;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetMyReportFeedbackServiceImpl implements GetMyReportFeedbackService {
    private final ReportHelper reportHelper;
    private final ReportMapper reportMapper;

    @Override
    public GetMyReportFeedbackResponseDto execute(User user, ReportType reportType, Pageable pageable) {
        Page<MyReportFeedbackVo> vos = reportHelper.findMyReportFeedbackVos(user, reportType, pageable);
        return reportMapper.toGetMyReportFeedbackResponseDto(vos);
    }
}
