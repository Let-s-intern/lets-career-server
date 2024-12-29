package org.letscareer.letscareer.global.batch.config.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.report.ReportRemindNotificationTasklet;
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
public class ReportRemindNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReportRemindNotificationTasklet reportRemindNotificationTasklet;

    @Bean
    public Job reportRemindNotificationJob() {
        return new JobBuilder("reportRemindNotificationJob", jobRepository)
                .start(sendReportRemindNotificationStep())
                .build();
    }

    @Bean
    protected Step sendReportRemindNotificationStep() {
        return new StepBuilder("sendReportRemindNotificationStep", jobRepository)
                .tasklet(reportRemindNotificationTasklet, transactionManager)
                .build();
    }
}
