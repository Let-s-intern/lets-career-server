package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.CreateReportRequestDto;

public interface CreateReportService {
    void execute(CreateReportRequestDto requestDto);
}
