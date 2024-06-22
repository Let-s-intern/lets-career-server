package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.live.entity.Live;
import org.letscareer.letscareer.domain.live.helper.LiveHelper;
import org.letscareer.letscareer.domain.live.type.MailStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@StepScope
public class UpdateMailStatusTasklet implements Tasklet {
    private final LiveHelper liveHelper;

    @Value("#{jobParameters[liveId]}")
    private Long liveId;

    @Value("#{jobParameters[mailStatus]}")
    private MailStatus mailStatus;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        Live live = liveHelper.findLiveByIdOrThrow(liveId);
        live.updateMailStatus(mailStatus);
        return RepeatStatus.FINISHED;
    }
}
