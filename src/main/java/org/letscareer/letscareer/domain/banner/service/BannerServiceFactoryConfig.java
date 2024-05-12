package org.letscareer.letscareer.domain.banner.service;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BannerServiceFactoryConfig {
    @Bean
    public ServiceLocatorFactoryBean bannerServiceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(BannerServiceFactory.class);
        return factoryBean;
    }
}
