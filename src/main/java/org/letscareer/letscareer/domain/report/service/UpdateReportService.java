package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface UpdateReportService {
    void execute(User user, Long reportId, UpdateReportRequestDto requestDto);
}
