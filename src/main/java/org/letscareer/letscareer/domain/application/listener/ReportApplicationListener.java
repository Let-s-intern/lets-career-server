package org.letscareer.letscareer.domain.application.listener;

import jakarta.persistence.PostPersist;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportIngParameter;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.global.common.utils.redis.service.ReportApplicationExpirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ReportApplicationListener {
    @Lazy
    @Autowired
    private ReportApplicationExpirationService reportApplicationExpirationService;
    @Lazy
    @Autowired
    private ReportOptionHelper reportOptionHelper;

    @PostPersist
    public void postPersist(ReportApplication reportApplication) {
        User user = reportApplication.getUser();
        Report report = reportApplication.getReport();
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportIngParameter reportIngParameter = ReportIngParameter.of(user.getName(), report.getTitle(), report.getType().getDesc(), reportOptionListStr, reportApplication.getId());
        reportApplicationExpirationService.saveWithExpire(reportIngParameter);
    }
}
