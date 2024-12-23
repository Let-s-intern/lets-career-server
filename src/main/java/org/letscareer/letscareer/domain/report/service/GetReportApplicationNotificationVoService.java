package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.vo.ReportApplicationNotificationVo;

public interface GetReportApplicationNotificationVoService {
    ReportApplicationNotificationVo execute(Long applicationId);
}
