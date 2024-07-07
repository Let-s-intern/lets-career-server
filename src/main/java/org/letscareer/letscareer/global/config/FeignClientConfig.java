package org.letscareer.letscareer.global.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.utils.toss.TossSecretKeyGenerator;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("org.letscareer.letscareer.global.common.utils.toss")
@RequiredArgsConstructor
@Configuration
public class FeignClientConfig {
    private final TossSecretKeyGenerator tossSecretKeyGenerator;

    @Bean
    public RequestInterceptor feignInterceptor() {
        String authorization = tossSecretKeyGenerator.generateSecretKey();
        System.out.println(authorization);
        return requestTemplate -> {
            requestTemplate.header("Authorization", authorization);
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
