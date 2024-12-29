package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.payment.entity.Payment;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.service.GetReportApplicationNotificationVoService;
import org.letscareer.letscareer.domain.report.vo.ReportApplicationNotificationVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.letscareer.letscareer.domain.report.service.impl.CreateReportApplicationServiceImpl.REPORT_APPLICATION_CACHE_KEY;

@RequiredArgsConstructor
@Transactional
@Service
public class GerReportApplicationNotificationVoServiceImpl implements GetReportApplicationNotificationVoService {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportOptionHelper reportOptionHelper;

    @Override
    @Cacheable(key = "#applicationId", value = REPORT_APPLICATION_CACHE_KEY)
    public ReportApplicationNotificationVo execute(Long applicationId) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrElseNull(applicationId);
        if(Objects.isNull(reportApplication)) return null;
        String userName = reportApplication.getUser().getName();
        Payment payment = reportApplication.getPayment();
        Report report = reportApplication.getReport();
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportFeedbackApplication reportFeedbackApplication = reportApplication.getReportFeedbackApplication();
        Boolean isFeedbackApplied = !Objects.isNull(reportFeedbackApplication);
        return ReportApplicationNotificationVo.of(userName, payment, "전체취소", report, reportOptionListStr, isFeedbackApplied);
    }
}
