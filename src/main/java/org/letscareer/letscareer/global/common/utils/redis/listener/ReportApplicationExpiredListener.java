package org.letscareer.letscareer.global.common.utils.redis.listener;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.global.common.utils.redis.service.ReportApplicationExpiredService;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class ReportApplicationExpiredListener extends RedisExpiredListener {
    private final static String reportApplicationKey = "ralets99";
    private final ReportApplicationExpiredService reportApplicationExpiredService;


    public ReportApplicationExpiredListener(RedisMessageListenerContainer listenerContainer, ReportApplicationExpiredService reportApplicationExpiredService) {
        super(listenerContainer);
        this.reportApplicationExpiredService = reportApplicationExpiredService;
    }

    @Override
    protected void handleExpiredKey(String expiredKey) {
        if (expiredKey.startsWith(reportApplicationKey)) {
            Long reportApplicationId = Long.valueOf(expiredKey.replace(reportApplicationKey, ""));
            reportApplicationExpiredService.sendKakaoMessage(reportApplicationId);
            reportApplicationExpiredService.updateReportApplicationStatus(reportApplicationId, ReportApplicationStatus.REPORTING);
        }
    }
}
