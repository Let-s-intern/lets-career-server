package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.dto.req.UpdateFeedbackScheduleRequestDto;
import org.letscareer.letscareer.domain.report.service.UpdateReportFeedbackSchedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportFeedbackScheduleImpl implements UpdateReportFeedbackSchedule {
    private final ReportApplicationHelper reportApplicationHelper;

    @Override
    public void execute(Long applicationId, UpdateFeedbackScheduleRequestDto requestDto) {
        ReportFeedbackApplication reportFeedbackApplication = reportApplicationHelper.findReportFeedbackApplicationByApplicationId(applicationId);
        reportFeedbackApplication.updateSchedule(requestDto);
    }
}
