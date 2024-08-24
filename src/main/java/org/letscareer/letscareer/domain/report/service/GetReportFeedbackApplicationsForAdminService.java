package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportFeedbackApplicationsForAdminResponseDto;
import org.springframework.data.domain.Pageable;

public interface GetReportFeedbackApplicationsForAdminService {
    GetReportFeedbackApplicationsForAdminResponseDto execute(Long reportId, Pageable pageable);
}
