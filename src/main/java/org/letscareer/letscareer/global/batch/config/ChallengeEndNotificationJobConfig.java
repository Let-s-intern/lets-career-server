package org.letscareer.letscareer.global.batch.config;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.ChallengeEndNotificationTasklet;
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
public class ChallengeEndNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ChallengeEndNotificationTasklet challengeEndNotificationTasklet;

    @Bean
    public Job challengeEndNotificationJob() {
        return new JobBuilder("challengeEndNotificationJob", jobRepository)
                .start(sendChallengeEndNotificationStep())
                .build();
    }

    @Bean
    protected Step sendChallengeEndNotificationStep() {
        return new StepBuilder("sendChallengeEndNotificationStep", jobRepository)
                .tasklet(challengeEndNotificationTasklet, transactionManager)
                .build();
    }
}
