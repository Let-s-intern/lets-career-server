package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.report.service.DeleteReportApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteReportApplicationServiceImpl implements DeleteReportApplicationService {
    private final ReportApplicationHelper reportApplicationHelper;

    @Override
    public void execute(Long reportApplicationId) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
        reportApplicationHelper.deleteReportApplication(reportApplication);
    }
}
