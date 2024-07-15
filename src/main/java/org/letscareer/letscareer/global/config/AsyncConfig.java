package org.letscareer.letscareer.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig extends AsyncConfigurerSupport {
    private final static String THREAD_EXECUTOR_BEAN_NAME = "threadPoolTaskExecutor";
    private final static String THREAD_EXECUTOR_PREFIX_NAME = "Executor-";

    @Bean(name = THREAD_EXECUTOR_BEAN_NAME)
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(3);
        taskExecutor.setThreadNamePrefix(THREAD_EXECUTOR_PREFIX_NAME);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
