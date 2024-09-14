package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportDetailForAdminResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface GetReportDetailForAdminService {
    GetReportDetailForAdminResponseDto execute(User user, Long reportId);
}
