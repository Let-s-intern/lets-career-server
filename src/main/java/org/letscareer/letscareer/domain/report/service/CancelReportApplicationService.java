package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.user.entity.User;

public interface CancelReportApplicationService {
    void execute(User user, Long reportApplicationId);
}
