package org.letscareer.letscareer.domain.application.listener;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.event.ReportApplicationExpireEvent;
import org.letscareer.letscareer.global.common.utils.redis.service.ReportApplicationExpirationService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportApplicationEventListener {
    private final ReportApplicationExpirationService reportApplicationExpirationService;

    @EventListener
    @Async
    public void listen(ReportApplicationExpireEvent reportApplicationExpireEvent) {
        reportApplicationExpirationService.sendKakaoMessage(reportApplicationExpireEvent.getReportApplicationId());
    }
}
