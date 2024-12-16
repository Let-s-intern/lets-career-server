package org.letscareer.letscareer.global.common.utils.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class ReportCancelExpirationListener extends KeyExpirationEventMessageListener {
    public ReportCancelExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        if (expiredKey.startsWith("report_application:")) {
            String reportApplicationId = expiredKey.substring("report_application:".length());
            handleReportAutoCancel(reportApplicationId);
        }
    }

    private void handleReportAutoCancel(String reportApplicationId) {
        // 환불 처리 로직
        System.out.println("ReportApplication ID " + reportApplicationId + " is being refunded.");
    }
}
