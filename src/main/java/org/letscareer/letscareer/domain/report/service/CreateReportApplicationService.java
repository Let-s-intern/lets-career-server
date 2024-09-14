package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.dto.res.CreateReportApplicationResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface CreateReportApplicationService {
    CreateReportApplicationResponseDto execute(User user, Long reportId, CreateReportApplicationRequestDto requestDto);
}
