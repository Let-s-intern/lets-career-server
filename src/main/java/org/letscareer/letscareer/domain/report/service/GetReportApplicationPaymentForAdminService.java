package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationPaymentForAdminResponseDto;

public interface GetReportApplicationPaymentForAdminService {
    GetReportApplicationPaymentForAdminResponseDto execute(Long reportId, Long applicationId);
}
