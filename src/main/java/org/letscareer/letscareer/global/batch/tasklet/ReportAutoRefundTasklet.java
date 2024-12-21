package org.letscareer.letscareer.global.batch.tasklet;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@StepScope
public class ReportAutoRefundTasklet implements Tasklet {

    @Value("#{jobParameters[reportApplicationId]}")
    private Long reportApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

        return RepeatStatus.FINISHED;
    }
}
