package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationOptionsForAdminResponseDto;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;

public interface GetReportApplicationPaymentForAdminService {
    GetReportApplicationOptionsForAdminResponseDto execute(Long reportId, Long applicationId, ReportPriceType priceType, String code);
}
