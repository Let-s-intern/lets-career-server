package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportFeedbackRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.repository.ReportFeedbackRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportFeedbackHelper {
    private final ReportFeedbackRepository reportFeedbackRepository;

    public ReportFeedback createReportFeedbackAndSave(CreateReportFeedbackRequestDto requestDto, Report report) {
        ReportFeedback reportFeedback = ReportFeedback.createReportFeedback(requestDto, report);
        return reportFeedbackRepository.save(reportFeedback);
    }
}
