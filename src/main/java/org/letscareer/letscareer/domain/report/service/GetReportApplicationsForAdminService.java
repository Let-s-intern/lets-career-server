package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationsForAdminResponseDto;
import org.springframework.data.domain.Pageable;

public interface GetReportApplicationsForAdminService {
    GetReportApplicationsForAdminResponseDto execute(Long reportId, Pageable pageable);
}
