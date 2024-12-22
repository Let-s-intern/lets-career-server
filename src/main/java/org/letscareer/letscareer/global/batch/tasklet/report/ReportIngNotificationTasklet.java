package org.letscareer.letscareer.global.batch.tasklet.report;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportIngParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.user.entity.User;
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
public class ReportIngNotificationTasklet implements Tasklet {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final NhnProvider nhnProvider;

    @Value("#{jobParameters[reportApplicationId]}")
    private Long reportApplicationId;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(reportApplicationId);
        Report report = reportApplication.getReport();
        User user = reportApplication.getUser();

        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportIngParameter reportIngParameter = ReportIngParameter.of(user.getName(), report.getTitle(), reportApplication.getReportPriceType().getDesc(), reportOptionListStr);
        nhnProvider.sendKakaoMessage(user, reportIngParameter, "report_ing");

        reportApplication.updateReportApplicationStatus(ReportApplicationStatus.REPORTING);
        return RepeatStatus.FINISHED;
    }
}
