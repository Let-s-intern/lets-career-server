package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateReportApplicationStatusRequestDto;

public interface UpdateReportApplicationStatusService {
    void execute(Long applicationId, UpdateReportApplicationStatusRequestDto requestDto);
}
