package org.letscareer.letscareer.global.common.utils.redis.listener;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.event.ReportApplicationExpireEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisEventListener implements MessageListener {
    private final ApplicationEventPublisher publisher;
    @Value("${spring.data.redis.report-application.key}")
    private String reportApplicationKey;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody());
        if(key.contains(reportApplicationKey)) {
            Long reportApplicationId = Long.valueOf(key.replace(reportApplicationKey, ""));
            publisher.publishEvent(ReportApplicationExpireEvent.of(reportApplicationId));
        }
    }
}
