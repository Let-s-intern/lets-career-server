package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;

public interface UpdateReportDocumentService {
    void execute(Long applicationId, UpdateReportDocumentRequestDto requestDto);
}
