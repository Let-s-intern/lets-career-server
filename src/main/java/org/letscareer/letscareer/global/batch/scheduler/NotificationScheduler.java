package org.letscareer.letscareer.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.program.helper.ProgramHelper;
import org.letscareer.letscareer.domain.program.vo.ProgramReviewNotificationVo;
import org.letscareer.letscareer.global.batch.config.ReviewNotificationJobConfig;
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
public class NotificationScheduler {
    private final JobLauncher jobLauncher;
    private final ReviewNotificationJobConfig reviewNotificationJobConfig;
    private final ProgramHelper programHelper;

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendReviewNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<ProgramReviewNotificationVo> programList = programHelper.findProgramReviewNotificationVos();
        for(ProgramReviewNotificationVo program : programList) {
            jobLauncher.run(
                    reviewNotificationJobConfig.reviewNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("programId", program.programId())
                            .addString("programType", program.programType().toString())
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }
}
