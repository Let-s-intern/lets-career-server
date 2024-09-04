package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.helper.ReportApplicationHelper;
import org.letscareer.letscareer.domain.nhn.dto.request.report.ReportDoneParameter;
import org.letscareer.letscareer.domain.nhn.provider.NhnProvider;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportDocumentRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.service.UpdateReportDocumentService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportDocumentServiceImpl implements UpdateReportDocumentService {
    private final ReportApplicationHelper reportApplicationHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final NhnProvider nhnProvider;

    @Override
    public void execute(Long applicationId, UpdateReportDocumentRequestDto requestDto) {
        ReportApplication reportApplication = reportApplicationHelper.findReportApplicationByReportApplicationIdOrThrow(applicationId);
        reportApplication.updateReportUrl(requestDto);
        sendKakaoMessage(reportApplication);
    }

    private void sendKakaoMessage(ReportApplication reportApplication) {
        User user = reportApplication.getUser();
        Report report = reportApplication.getReport();
        String reportOptionListStr = reportOptionHelper.createReportOptionListStr(reportApplication.getReportApplicationOptionList());
        ReportDoneParameter reportDoneParameter = ReportDoneParameter.of(user.getName(), report.getTitle(), report.getType().getDesc(), reportOptionListStr);
        nhnProvider.sendKakaoMessage(reportApplication.getUser(), reportDoneParameter, "report_done");
    }
}
