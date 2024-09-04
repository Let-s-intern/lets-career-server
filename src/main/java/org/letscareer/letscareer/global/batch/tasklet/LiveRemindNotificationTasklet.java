package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.live.LiveClassRemindParameter;
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
public class LiveRemindNotificationTasklet implements Tasklet {
    private final LiveHelper liveHelper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[liveId]}")
    private Long liveId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Live live = liveHelper.findLiveByIdOrThrow(liveId);
        List<User> userList = liveApplicationHelper.getRemindNotificationUsers(liveId);
        if(!userList.isEmpty()) {
            List<LiveClassRemindParameter> requestParameterList = userList.stream()
                    .map(user -> LiveClassRemindParameter.of(user.getName(), live))
                    .collect(Collectors.toList());
            nhnProvider.sendKakaoMessages(userList, requestParameterList, "liveclass_remind");
        }
        return RepeatStatus.FINISHED;
    }
}
