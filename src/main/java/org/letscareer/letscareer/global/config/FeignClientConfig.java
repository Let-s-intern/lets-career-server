package org.letscareer.letscareer.global.config;

import org.letscareer.letscareer.LetsCareerApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackageClasses = LetsCareerApplication.class)
@Configuration
public class FeignClientConfig {
}
