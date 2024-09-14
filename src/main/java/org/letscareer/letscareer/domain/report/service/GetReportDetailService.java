package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportDetailResponseDto;

public interface GetReportDetailService {
    GetReportDetailResponseDto execute(Long reportId);
}
