package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateFeedbackScheduleRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface UpdateReportFeedbackSchedule {
    void execute(User user, Long reportId, Long applicationId, UpdateFeedbackScheduleRequestDto requestDto);
}
