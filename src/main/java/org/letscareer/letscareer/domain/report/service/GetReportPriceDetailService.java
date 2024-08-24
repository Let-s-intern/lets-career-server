package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportPriceDetailResponseDto;

public interface GetReportPriceDetailService {
    GetReportPriceDetailResponseDto execute(Long reportId);
}
