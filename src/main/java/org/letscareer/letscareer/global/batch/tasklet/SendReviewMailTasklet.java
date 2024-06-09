package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.helper.LiveApplicationHelper;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.vo.LiveEmailVo;
import org.letscareer.letscareer.global.common.utils.EmailUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@StepScope
public class SendReviewMailTasklet implements Tasklet {
    private final LiveHelper liveHelper;
    private final LiveApplicationHelper liveApplicationHelper;
    private final EmailUtils emailUtils;

    @Value("#{jobParameters[liveId]}")
    private Long liveId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        LiveEmailVo liveEmailVo = liveHelper.findLiveEmailVoByLiveId(liveId);
        List<String> emailAddressList = liveApplicationHelper.findEmailListByLiveId(liveId);
        if(!emailAddressList.isEmpty()) emailUtils.sendReviewMail(emailAddressList, liveEmailVo);
        return RepeatStatus.FINISHED;
    }
}
