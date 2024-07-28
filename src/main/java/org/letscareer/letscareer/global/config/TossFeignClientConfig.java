package org.letscareer.letscareer.global.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.utils.toss.TossSecretKeyGenerator;
import org.letscareer.letscareer.global.error.toss.TossErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("org.letscareer.letscareer.global.common.utils.toss")
@RequiredArgsConstructor
@Configuration
public class TossFeignClientConfig {
    private final TossSecretKeyGenerator tossSecretKeyGenerator;

    @Bean
    public RequestInterceptor tossFeignInterceptor() {
        String authorization = tossSecretKeyGenerator.generateSecretKey();
        return requestTemplate -> {
            requestTemplate.header("Authorization", authorization);
            requestTemplate.header("Content-Type", "application/json");
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new TossErrorDecoder();
    }
}
