package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetMyReportFeedbackResponseDto;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Pageable;

public interface GetMyReportFeedbackService {
    GetMyReportFeedbackResponseDto execute(User user, ReportType reportType, Pageable pageable);
}
