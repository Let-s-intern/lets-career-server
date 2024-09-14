package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;

public interface UpdateReportService {
    void execute(Long reportId, UpdateReportRequestDto requestDto);
}
