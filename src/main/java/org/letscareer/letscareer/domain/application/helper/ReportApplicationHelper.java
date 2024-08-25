package org.letscareer.letscareer.domain.application.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplication;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.application.entity.report.ReportFeedbackApplication;
import org.letscareer.letscareer.domain.application.repository.report.ReportApplicationOptionRepository;
import org.letscareer.letscareer.domain.application.repository.report.ReportApplicationRepository;
import org.letscareer.letscareer.domain.application.repository.report.ReportFeedbackApplicationRepository;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportApplicationRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.domain.user.entity.User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportApplicationHelper {
    private final ReportApplicationRepository reportApplicationRepository;
    private final ReportApplicationOptionRepository reportApplicationOptionRepository;
    private final ReportFeedbackApplicationRepository reportFeedbackApplicationRepository;

    public ReportApplication createReportApplicationAndSave(CreateReportApplicationRequestDto requestDto,
                                                            Report report,
                                                            User user) {
        ReportApplication reportApplication
                = ReportApplication.createReportApplication(requestDto, report, user);
        return reportApplicationRepository.save(reportApplication);
    }

    public ReportApplicationOption createReportApplicationOptionAndSave(ReportApplication reportApplication,
                                                                        ReportOption reportOption) {
        ReportApplicationOption reportApplicationOption
                = ReportApplicationOption.createReportApplicationOption(reportApplication, reportOption);
        return reportApplicationOptionRepository.save(reportApplicationOption);
    }

    public ReportFeedbackApplication createReportFeedbackApplicationAndSave(CreateReportApplicationRequestDto requestDto,
                                                                            ReportFeedback reportFeedback,
                                                                            ReportApplication reportApplication) {
        ReportFeedbackApplication reportFeedbackApplication
                = ReportFeedbackApplication.createReportFeedbackApplication(requestDto, reportFeedback, reportApplication);
        return reportFeedbackApplicationRepository.save(reportFeedbackApplication);
    }
}
