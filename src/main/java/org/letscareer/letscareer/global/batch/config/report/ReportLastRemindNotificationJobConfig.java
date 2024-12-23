package org.letscareer.letscareer.global.batch.config.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.global.batch.tasklet.report.ReportLastRemindNotificationTasklet;
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
public class ReportLastRemindNotificationJobConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final ReportLastRemindNotificationTasklet reportLastRemindNotificationTasklet;

    @Bean
    public Job reportLastRemindNotificationJob() {
        return new JobBuilder("reportLastRemindNotificationJob", jobRepository)
                .start(sendReportLastRemindNotificationStep())
                .build();
    }

    @Bean
    protected Step sendReportLastRemindNotificationStep() {
        return new StepBuilder("sendReportLastRemindNotificationStep", jobRepository)
                .tasklet(reportLastRemindNotificationTasklet, transactionManager)
                .build();
    }
}
