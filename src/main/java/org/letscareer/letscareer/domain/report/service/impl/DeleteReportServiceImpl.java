package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.service.DeleteReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteReportServiceImpl implements DeleteReportService {
    private final ReportHelper reportHelper;

    @Override
    public void execute(Long reportId) {
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        reportHelper.deleteReport(report);
    }
}
