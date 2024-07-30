package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ChallengeRemindNotificationTasklet;
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
public class ChallengeRemindNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ChallengeRemindNotificationTasklet challengeRemindNotificationTasklet;

    @Bean
    public Job challengeRemindNotificationJob() {
        return new JobBuilder("challengeRemindNotificationJob", jobRepository)
                .start(sendchallengeRemindNotificationStep())
                .build();
    }

    @Bean
    protected Step sendchallengeRemindNotificationStep() {
        return new StepBuilder("sendChallengeRemindNotificationStep", jobRepository)
                .tasklet(challengeRemindNotificationTasklet, transactionManager)
                .build();
    }
}
