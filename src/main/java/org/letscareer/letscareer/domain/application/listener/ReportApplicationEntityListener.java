package org.letscareer.letscareer.domain.application.listener;

import jakarta.persistence.PostPersist;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.global.common.utils.redis.service.ReportApplicationExpirationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ReportApplicationEntityListener {
    @Lazy
    @Autowired
    private ReportApplicationExpirationService reportApplicationExpirationService;

    @PostPersist
    public void postPersist(ReportApplication reportApplication) {
        reportApplicationExpirationService.setWithExpire(reportApplication.getId());
    }
}
