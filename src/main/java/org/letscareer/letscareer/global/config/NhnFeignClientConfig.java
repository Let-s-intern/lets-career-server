package org.letscareer.letscareer.global.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.common.utils.nhn.NhnSecretKeyReader;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("org.letscareer.letscareer.global.common.utils.nhn")
@RequiredArgsConstructor
@Configuration
public class NhnFeignClientConfig {
    private final NhnSecretKeyReader nhnSecretKeyReader;

    @Bean
    public RequestInterceptor ngnFeignInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("X-Secret-Key", nhnSecretKeyReader.getSecretKey());
            requestTemplate.header("Content-Type", "application/json");
        };
    }
}
