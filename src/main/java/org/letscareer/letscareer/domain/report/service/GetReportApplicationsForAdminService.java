package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.springframework.data.domain.Pageable;

public interface GetReportApplicationsForAdminService {
    GetReportApplicationsForAdminResponseDto execute(Long reportId, ReportType reportType, ReportPriceType priceType, Boolean isApplyFeedback, Pageable pageable);
}
