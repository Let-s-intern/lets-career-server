package org.letscareer.letscareer.global.batch.config.challenge;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.challenge.MissionEndNotificationTasklet;
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
public class MissionEndNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final MissionEndNotificationTasklet missionEndNotificationTasklet;

    @Bean
    public Job missionEndNotificationJob() {
        return new JobBuilder("missionEndNotificationJob", jobRepository)
                .start(sendMissionEndNotificationStep())
                .build();
    }

    @Bean
    protected Step sendMissionEndNotificationStep() {
        return new StepBuilder("sendMissionEndNotificationStep", jobRepository)
                .tasklet(missionEndNotificationTasklet, transactionManager)
                .build();
    }
}
