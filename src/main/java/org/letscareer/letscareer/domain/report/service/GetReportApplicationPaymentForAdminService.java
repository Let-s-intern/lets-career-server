package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationOptionsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;
import org.letscareer.letscareer.domain.report.type.ReportType;

public interface GetReportApplicationPaymentForAdminService {
    GetReportApplicationOptionsForAdminResponseDto execute(Long reportId, Long applicationId, ReportType reportType, ReportPriceType priceType, String code);
}
