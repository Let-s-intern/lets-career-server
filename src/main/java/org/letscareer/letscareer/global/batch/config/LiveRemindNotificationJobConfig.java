package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.LiveRemindNotificationTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@EnableBatchProcessing
@RequiredArgsConstructor
@Configuration
public class LiveRemindNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final LiveRemindNotificationTasklet liveRemindNotificationTasklet;

    @Bean
    public Job liveRemindNotificationJob() {
        return new JobBuilder("liveRemindNotificationJob", jobRepository)
                .start(sendLiveRemindNotificationStep())
                .build();
    }

    @Bean
    protected Step sendLiveRemindNotificationStep() {
        return new StepBuilder("sendLiveRemindNotificationStep", jobRepository)
                .tasklet(liveRemindNotificationTasklet, transactionManager)
                .build();
    }
}
