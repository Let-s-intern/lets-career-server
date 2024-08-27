package org.letscareer.letscareer.domain.report.service;

import org.letscareer.letscareer.domain.report.dto.res.GetReportPaymentResponseDto;
import org.letscareer.letscareer.domain.user.entity.User;

public interface GetReportPaymentService {
    GetReportPaymentResponseDto execute(User user, Long applicationId);
}
