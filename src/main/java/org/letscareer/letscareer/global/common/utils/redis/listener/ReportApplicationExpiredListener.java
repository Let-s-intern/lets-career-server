package org.letscareer.letscareer.global.common.utils.redis.listener;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.utils.redis.service.ReportApplicationExpiredService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportApplicationExpiredListener extends EventListener {
    private final ReportApplicationExpiredService reportApplicationExpiredService;

    @Value("${spring.data.redis.report-application.key}")
    private String reportApplicationKey;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody());
        if(key.contains(reportApplicationKey)) {
            Long reportApplicationId = Long.valueOf(key.replace(reportApplicationKey, ""));
            reportApplicationExpiredService.sendKakaoMessage(reportApplicationId);
        }
    }
}
