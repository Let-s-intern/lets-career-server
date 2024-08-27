package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateFeedbackScheduleRequestDto;

public interface UpdateReportFeedbackSchedule {
    void execute(Long reportId, Long applicationId, UpdateFeedbackScheduleRequestDto requestDto);
}
