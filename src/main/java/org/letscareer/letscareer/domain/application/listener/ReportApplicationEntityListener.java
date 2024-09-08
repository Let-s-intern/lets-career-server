package org.letscareer.letscareer.domain.application.listener;

import jakarta.persistence.PostPersist;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.global.common.utils.redis.service.ReportApplicationExpiredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ReportApplicationEntityListener {
    @Lazy
    @Autowired
    private ReportApplicationExpiredService reportApplicationExpiredService;

    @PostPersist
    public void postPersist(ReportApplication reportApplication) {
        reportApplicationExpiredService.setWithExpire(reportApplication.getId());
    }
}
