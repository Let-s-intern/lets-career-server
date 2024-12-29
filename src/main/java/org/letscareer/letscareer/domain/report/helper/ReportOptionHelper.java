package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.application.entity.report.ReportApplicationOption;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportOptionRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportOption;
import org.letscareer.letscareer.domain.report.repository.ReportOptionRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ReportOptionHelper {
    private final ReportOptionRepository reportOptionRepository;

    public ReportOption createReportOptionAndSave(CreateReportOptionRequestDto requestDto, Report report) {
        ReportOption reportOption = ReportOption.createReportOption(requestDto, report);
        return reportOptionRepository.save(reportOption);
    }

    public String createReportOptionListStr(List<ReportApplicationOption> reportApplicationOptions) {
        if(reportApplicationOptions.isEmpty()) return "없음";
        return reportApplicationOptions.stream()
                .map(option -> {
                    if(option.getTitle().startsWith("+")) return option.getTitle().substring(1);
                    else return option.getTitle();
                })
                .distinct()
                .collect(Collectors.joining(", "));
    }

    public List<ReportOption> findReportOptionsByReportIdAndOptionIds(Long reportId, List<Long> optionIds) {
        if (Objects.isNull(optionIds) || optionIds.isEmpty()) return new ArrayList<>();
        return reportOptionRepository.findReportOptionsByReportIdAndOptionIds(reportId, optionIds);
    }

    public void deleteAllReportOptionsByReportId(Long reportId) {
        reportOptionRepository.deleteAllByReportId(reportId);
    }
}
