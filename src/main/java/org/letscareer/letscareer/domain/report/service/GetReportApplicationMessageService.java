package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportApplicationMessageResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface GetReportApplicationMessageService {
    GetReportApplicationMessageResponseDto execute(User user, Long applicationId);
}
