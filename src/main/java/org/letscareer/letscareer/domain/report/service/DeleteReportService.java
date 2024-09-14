package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.user.entity.User;

public interface DeleteReportService {
    void execute(User user, Long reportId);
}
