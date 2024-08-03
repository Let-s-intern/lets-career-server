package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.challenge.entity.Challenge;
import org.letscareer.letscareer.domain.challenge.helper.ChallengeHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.OTRemindParameter;
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
public class ChallengeOTRemindNotificationTasklet implements Tasklet {
    private final ChallengeHelper challengeHelper;
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[challengeId]}")
    private Long challengeId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Challenge challenge = challengeHelper.findChallengeByIdOrThrow(challengeId);
        List<User> userList = challengeApplicationHelper.getNotificationUsers(challengeId);
        if(!userList.isEmpty()) {
            List<OTRemindParameter> requestParameterList = userList.stream()
                    .map(user -> OTRemindParameter.of(user.getName(), challenge))
                    .collect(Collectors.toList());
            nhnProvider.sendKakaoMessages(userList, requestParameterList, "OT_remind");
        }
        return RepeatStatus.FINISHED;
    }
}
