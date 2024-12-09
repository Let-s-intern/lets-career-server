package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportTitleResponseDto;

public interface GetReportTitleService {
    GetReportTitleResponseDto getReportTitle(Long reportId);
}
