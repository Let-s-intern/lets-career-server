package org.letscareer.letscareer.global.batch.config.challenge;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.challenge.ChallengeEventNotificationTasklet;
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
public class ChallengeEventNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ChallengeEventNotificationTasklet challengeEventNotificationTasklet;

    @Bean
    public Job challengeEventNotificationJob() {
        return new JobBuilder("challengeEventNotificationJob", jobRepository)
                .start(sendChallengeEventNotificationStep())
                .build();
    }

    @Bean
    protected Step sendChallengeEventNotificationStep() {
        return new StepBuilder("sendChallengeEventNotificationStep", jobRepository)
                .tasklet(challengeEventNotificationTasklet, transactionManager)
                .build();
    }
}
