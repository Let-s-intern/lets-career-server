package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.vo.NotificationUserVo;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.challenge.ChallengeEndParameter;
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
public class ChallengeEndNotificationTasklet implements Tasklet {
    private final ChallengeHelper challengeHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[challengeId]}")
    private Long challengeId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        List<NotificationUserVo> notificationUserVos = challengeApplicationHelper.getNotificationUserVos(challengeId);
        if(!notificationUserVos.isEmpty()) {
            List<ChallengeEndParameter> requestParameterList = notificationUserVos.stream()
                    .map(notificationUserVo -> ChallengeEndParameter.of(notificationUserVo.user().getName(), challenge.getTitle(), challenge.getId(), notificationUserVo.applicationId()))
                    .collect(Collectors.toList());
            List<User> userList = notificationUserVos.stream()
                    .map(notificationUserVo -> notificationUserVo.user())
                    .collect(Collectors.toList());
            nhnProvider.sendKakaoMessages(userList, requestParameterList, "challenge_end");
        }
        return RepeatStatus.FINISHED;
    }
}
