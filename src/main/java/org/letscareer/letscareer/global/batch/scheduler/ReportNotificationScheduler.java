package org.letscareer.letscareer.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.ReportFeedbackApplicationHelper;
import org.letscareer.letscareer.global.batch.config.*;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportNotificationScheduler {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportFeedbackApplicationHelper reportFeedbackApplicationHelper;
    private final JobLauncher jobLauncher;
    private final ReportFeedbackDdayNotificationJobConfig reportFeedbackDdayNotificationJobConfig;
    private final ReportIngNotificationJobConfig reportIngNotificationJobConfig;


    @Scheduled(cron = "0 0 8 * * ?")
    @SchedulerLock(name = "reportFeedbackDdayNotificationJob", lockAtMostFor = "3m", lockAtLeastFor = "3m")
    public void sendReportFeedbackDdayNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportFeedbackApplicationList = reportFeedbackApplicationHelper.findDdayNotificationReportFeedbackApplicationIds();
        for(Long reportFeedbackApplicationId: reportFeedbackApplicationList) {
            jobLauncher.run(
                    reportFeedbackDdayNotificationJobConfig.reportFeedbackDdayNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportFeedbackApplicationId", reportFeedbackApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 0/1 * * * *")
    @SchedulerLock(name = "reportIngNotificationJob", lockAtMostFor = "59s", lockAtLeastFor = "59s")
    public void sendReportIngNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> reportApplicationList = reportApplicationHelper.findIngNotificationReportApplicationIds();
        for(Long reportApplicationId : reportApplicationList) {
            jobLauncher.run(
                    reportIngNotificationJobConfig.reportIngNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("reportApplicationId", reportApplicationId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }
}
