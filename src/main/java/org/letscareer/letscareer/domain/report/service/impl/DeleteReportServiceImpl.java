package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.service.DeleteReportService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.helper.UserHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteReportServiceImpl implements DeleteReportService {
    private final UserHelper userHelper;
    private final ReportHelper reportHelper;

    @Override
    public void execute(User user, Long reportId) {
        userHelper.validateAdminUser(user);
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        reportHelper.deleteReport(report);
    }
}
