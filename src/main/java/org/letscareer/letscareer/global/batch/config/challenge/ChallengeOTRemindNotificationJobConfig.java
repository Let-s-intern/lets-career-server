package org.letscareer.letscareer.global.batch.config.challenge;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.challenge.ChallengeOTRemindNotificationTasklet;
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
public class ChallengeOTRemindNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ChallengeOTRemindNotificationTasklet challengeOTRemindNotificationTasklet;

    @Bean
    public Job challengeOTRemindNotificationJob() {
        return new JobBuilder("challengeOTRemindNotificationJob", jobRepository)
                .start(sendChallengeOTRemindNotificationStep())
                .build();
    }

    @Bean
    protected Step sendChallengeOTRemindNotificationStep() {
        return new StepBuilder("sendChallengeOTRemindNotificationStep", jobRepository)
                .tasklet(challengeOTRemindNotificationTasklet, transactionManager)
                .build();
    }
}
