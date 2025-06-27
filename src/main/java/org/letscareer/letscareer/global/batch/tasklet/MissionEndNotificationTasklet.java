package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.vo.NotificationUserVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.mission.entity.Mission;
import org.letscareer.letscareer.domain.mission.helper.MissionHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.challenge.MissionEndParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@StepScope
public class MissionEndNotificationTasklet implements Tasklet {
    private final MissionHelper missionHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[missionId]}")
    private Long missionId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Mission mission = missionHelper.findMissionByIdOrThrow(missionId);
        Challenge challenge = mission.getChallenge();
        List<NotificationUserVo> userList = challengeApplicationHelper.getAttendanceNullNotificationUserVos(challenge.getId(), mission.getId());
        if(!userList.isEmpty()) {
            List<MissionEndParameter> requestParameterList = userList.stream()
                    .map(notificationUserVo -> MissionEndParameter.of(notificationUserVo.user().getName(), mission, challenge, notificationUserVo.applicationId()))
                    .collect(Collectors.toList());
            nhnProvider.sendKakaoMessages(userList, requestParameterList, "mission_end");
        }
        return RepeatStatus.FINISHED;
    }
}
