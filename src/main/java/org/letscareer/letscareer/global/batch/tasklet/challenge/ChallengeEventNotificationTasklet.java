package org.letscareer.letscareer.global.batch.tasklet.challenge;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.vo.NotificationUserVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.challenge.ChallengeEventParameter;
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
public class ChallengeEventNotificationTasklet implements Tasklet {
    private final ChallengeHelper challengeHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[challengeId]}")
    private Long challengeId;

    @Value("#{jobParameters[missionId]}")
    private Long missionId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        List<NotificationUserVo> notificationUserVos = challengeApplicationHelper.getAttendanceNullNotificationUserVos(challengeId, missionId);
        if(!notificationUserVos.isEmpty()) {
            List<ChallengeEventParameter> requestParameterList = notificationUserVos.stream()
                    .map(notificationUserVo -> ChallengeEventParameter.of(notificationUserVo.user().getName(), challenge.getTitle(),challenge.getEndDate().plusDays(2), challenge.getId(), notificationUserVo.applicationId(), missionId))
                    .collect(Collectors.toList());
            List<User> userList = notificationUserVos.stream()
                    .map(notificationUserVo -> notificationUserVo.user())
                    .collect(Collectors.toList());
            nhnProvider.sendKakaoMessages(userList, requestParameterList, "challenge_event_test");
        }
        return RepeatStatus.FINISHED;
    }
}
