package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportDetailForAdminResponseDto;

public interface GetReportDetailForAdminService {
    GetReportDetailForAdminResponseDto execute(Long reportId);
}
