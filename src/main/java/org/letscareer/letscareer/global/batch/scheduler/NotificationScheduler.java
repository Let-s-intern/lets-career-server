package org.letscareer.letscareer.global.batch.scheduler;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.program.helper.ProgramHelper;
import org.letscareer.letscareer.domain.program.vo.ProgramReviewNotificationVo;
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
public class NotificationScheduler {
    private final ProgramHelper programHelper;
    private final ChallengeHelper challengeHelper;
    private final LiveHelper liveHelper;
    private final MissionHelper missionHelper;
    private final JobLauncher jobLauncher;
    private final ReviewNotificationJobConfig reviewNotificationJobConfig;
    private final ChallengeRemindNotificationJobConfig challengeRemindNotificationJobConfig;
    private final LiveRemindNotificationJobConfig liveRemindNotificationJobConfig;
    private final ChallengeEndNotificationJobConfig challengeEndNotificationJobConfig;
    private final ChallengeOTRemindNotificationJobConfig challengeOTRemindNotificationJobConfig;
    private final MissionEndNotificationJobConfig missionEndNotificationJobConfig;

    @Scheduled(cron = "0 5 10 * * ?")
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

    @Scheduled(cron = "0 5 9 * * ?")
    public void sendChallengeRemindNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> challengeIdList = challengeHelper.findRemindNotificationChallengeIds();
        for(Long challengeId : challengeIdList) {
            jobLauncher.run(
                    challengeRemindNotificationJobConfig.challengeRemindNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("challengeId", challengeId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 10 9 * * ?")
    public void sendLiveRemindNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> liveIdList = liveHelper.findRemindNotificationLiveIds();
        for(Long liveId : liveIdList) {
            jobLauncher.run(
                    liveRemindNotificationJobConfig.liveRemindNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("liveId", liveId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 0 18 * * ?")
    public void sendChallengeEndNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> challengeIdList = challengeHelper.findEndNotificationChallengeIds();
        for(Long challengeId : challengeIdList) {
            jobLauncher.run(
                    challengeEndNotificationJobConfig.challengeEndNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("challengeId", challengeId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 0 9-12 * * ?")
    public void sendChallengeOTRemindNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> challengeIdList = challengeHelper.findOTRemindNotificationChallengeIds();
        for(Long challengeId : challengeIdList) {
            jobLauncher.run(
                    challengeOTRemindNotificationJobConfig.challengeOTRemindNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("challengeId", challengeId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }

//    @Scheduled(cron = "0 5 18 * * ?")
    @Scheduled(fixedDelay = 50000)
    public void sendMissionEndNotification() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Long> missionIdList = missionHelper.findEndNotificationMissionIds();
        for(Long missionId : missionIdList) {
            jobLauncher.run(
                    missionEndNotificationJobConfig.missionEndNotificationJob(),
                    new JobParametersBuilder()
                            .addLong("missionId", missionId)
                            .addLocalDateTime("now", LocalDateTime.now())
                            .toJobParameters()
            );
        }
    }
}
