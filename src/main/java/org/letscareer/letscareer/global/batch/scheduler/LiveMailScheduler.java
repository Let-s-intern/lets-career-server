package org.letscareer.letscareer.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.type.MailStatus;
import org.letscareer.letscareer.global.batch.config.RemindMailJobConfig;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LiveMailScheduler {
    private final JobLauncher jobLauncher;
    private final RemindMailJobConfig remindMailJobConfig;
    private final LiveHelper liveHelper;

//    @Scheduled(cron = "0 0 9 * * ?")
    @Scheduled(fixedDelay = 1000000)
    public void sendRemindMail() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> liveIdList = liveHelper.findRemindMailLiveIdList();
        for(Long liveId : liveIdList) {
            jobLauncher.run(
                    remindMailJobConfig.remindMailJob(),
                    new JobParametersBuilder()
                            .addLong("liveId", liveId)
                            .addString("mailStatus", String.valueOf(MailStatus.REVIEW))
                            .toJobParameters()
            );
        }
    }
}
