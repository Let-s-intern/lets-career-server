package org.letscareer.letscareer.global.config;

import org.letscareer.letscareer.domain.application.service.ApplicationServiceFactory;
import org.letscareer.letscareer.domain.pg.provider.PgProviderFactory;
import org.letscareer.letscareer.domain.review.service.ReviewServiceFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {
    @Bean
    public ServiceLocatorFactoryBean applicationServiceFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ApplicationServiceFactory.class);
        return factoryBean;
    }

    @Bean
    public ServiceLocatorFactoryBean pgProviderFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(PgProviderFactory.class);
        return factoryBean;
    }

    @Bean
    public ServiceLocatorFactoryBean reviewServiceFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ReviewServiceFactory.class);
        return factoryBean;
    }
}
