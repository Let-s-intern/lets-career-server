package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportDoneParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportApplicationStatusRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.service.UpdateReportApplicationStatusService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportApplicationStatusServiceImpl implements UpdateReportApplicationStatusService {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final NhnProvider nhnProvider;

    @Override
    public void execute(Long applicationId, UpdateReportApplicationStatusRequestDto requestDto) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        ReportApplicationStatus currentStatus = reportApplication.getStatus();
        reportApplication.updateReportStatus(requestDto);
        if(validateCompletedReportApplication(currentStatus, requestDto.status())) sendKakaoMessage(reportApplication);
    }

    private boolean validateCompletedReportApplication(ReportApplicationStatus currentStatus, ReportApplicationStatus newStatus) {
        if(Objects.isNull(newStatus)) return false;
        return currentStatus.equals(ReportApplicationStatus.REPORTED) && newStatus.equals(ReportApplicationStatus.COMPLETED);
    }

    private void sendKakaoMessage(ReportApplication reportApplication) {
        User user = reportApplication.getUser();
        Report report = reportApplication.getReport();
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportDoneParameter reportDoneParameter = ReportDoneParameter.of(user.getName(), report.getTitle(), report.getType().getDesc(), reportOptionListStr);
        nhnProvider.sendKakaoMessage(reportApplication.getUser(), reportDoneParameter, "report_done");
    }
}
