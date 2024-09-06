package org.letscareer.letscareer.global.common.utils.redis.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportApplicationExpirationListener implements MessageListener {
    private final ApplicationEventPublisher publisher;
    @Value("${spring.data.redis.report-application.key}")
    private String reportApplicationKey;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = new String(message.getBody());
        if(key.contains(reportApplicationKey)) {
            // ::TODO 서류진단 진행중 알림톡 발송
        }
    }
}
