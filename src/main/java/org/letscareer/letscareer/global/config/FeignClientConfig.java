package org.letscareer.letscareer.global.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("org.letscareer.letscareer.global.common.utils.toss")
@Configuration
public class FeignClientConfig {
}
