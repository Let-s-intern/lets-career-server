package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportOptionRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.domain.report.repository.ReportOptionRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportOptionHelper {
    private final ReportOptionRepository reportOptionRepository;

    public ReportOption createReportOptionAndSave(CreateReportOptionRequestDto requestDto, Report report) {
        ReportOption reportOption = ReportOption.createReportOption(requestDto, report);
        return reportOptionRepository.save(reportOption);
    }
}
