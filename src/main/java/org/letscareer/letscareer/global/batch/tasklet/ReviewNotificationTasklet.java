package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.ReviewParameter;
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
public class ReviewNotificationTasklet implements Tasklet {
    private final ChallengeApplicationHelper challengeApplicationHelper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[programId]}")
    private Long programId;

    @Value("#{jobParameters[programType]}")
    private String programType;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        List<User> userList = null;
        if(programType.equals("CHALLENGE")) userList = challengeApplicationHelper.getApplicationNotificationUsers(programId);
        else userList = liveApplicationHelper.getApplicationNotificationUsers(programId);
        if(userList != null && !userList.isEmpty()) {
            List<ReviewParameter> requestParameterList = userList.stream()
                    .map(user -> ReviewParameter.of(user.getName(), programType, programId))
                    .collect(Collectors.toList());
            nhnProvider.sendKakaoMessages(userList, requestParameterList, "review");
        }
        return RepeatStatus.FINISHED;
    }
}
