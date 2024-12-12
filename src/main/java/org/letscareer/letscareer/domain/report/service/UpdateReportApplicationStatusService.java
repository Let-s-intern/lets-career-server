package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateReportApplicationStatusRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface UpdateReportApplicationStatusService {
    void execute(User user, Long applicationId, UpdateReportApplicationStatusRequestDto requestDto);
}
