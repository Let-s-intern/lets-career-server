package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetMyReportResponseDto;
import org.letscareer.letscareer.domain.report.type.ReportType;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.data.domain.Pageable;

public interface GetMyReportService {
    GetMyReportResponseDto execute(User user, ReportType reportType, Pageable pageable);
}
