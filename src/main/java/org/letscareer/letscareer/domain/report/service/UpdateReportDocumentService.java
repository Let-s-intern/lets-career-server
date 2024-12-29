package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface UpdateReportDocumentService {
    void execute(User user, Long applicationId, UpdateReportDocumentRequestDto requestDto);
}
