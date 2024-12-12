package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.UpdateMyReportApplicationRequestDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface UpdateMyReportApplicationService {
    void execute(User user, Long applicationId, UpdateMyReportApplicationRequestDto requestDto);
}
