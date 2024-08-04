package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.ChallengeApplicationHelper;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.application.vo.ReviewNotificationUserVo;
import org.letscareer.letscareer.domain.nhn.dto.request.ReviewParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
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
        List<ReviewNotificationUserVo> userList = null;
        if(programType.equals("CHALLENGE")) userList = challengeApplicationHelper.getReviewNotificationUserVos(programId);
        else userList = liveApplicationHelper.getReviewNotificationUserVos(programId);
        if(userList != null && !userList.isEmpty()) {
            List<ReviewParameter> requestParameterList = userList.stream()
                    .map(userVo -> ReviewParameter.of(userVo.name(), programType, programId, userVo.applicationId()))
                    .collect(Collectors.toList());
            nhnProvider.sendReviewKakaoMessages(userList, requestParameterList, "review");
        }
        return RepeatStatus.FINISHED;
    }
}
