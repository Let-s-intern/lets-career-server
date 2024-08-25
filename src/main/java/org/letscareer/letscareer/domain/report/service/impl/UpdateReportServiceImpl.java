package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportFeedbackRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportOptionRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.helper.ReportFeedbackHelper;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
import org.letscareer.letscareer.domain.report.service.UpdateReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportServiceImpl implements UpdateReportService {
    private final ReportHelper reportHelper;
    private final ReportPriceHelper reportPriceHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final ReportFeedbackHelper reportFeedbackHelper;

    @Override
    public void execute(Long reportId, UpdateReportRequestDto requestDto) {
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        report.updateReport(requestDto);
        updateReportPrices(requestDto.priceInfo(), report);
        updateReportOptions(requestDto.optionInfo(), report);
        updateReportFeedback(requestDto.feedbackInfo(), report);
    }

    private void updateReportPrices(List<CreateReportPriceRequestDto> priceInfo, Report report) {
        if(Objects.isNull(priceInfo)) return;
        reportPriceHelper.deleteAllReportPricesByReportId(report.getId());
        report.setInitReportPrices();
        createReportPricesAndSave(priceInfo, report);
    }

    private void updateReportOptions(List<CreateReportOptionRequestDto> optionInfo, Report report) {
        if(Objects.isNull((optionInfo))) return;
        reportOptionHelper.deleteAllReportOptionsByReportId(report.getId());
        report.setInitReportOptions();
        createReportOptionsAndSave(optionInfo, report);
    }

    private void updateReportFeedback(CreateReportFeedbackRequestDto feedbackInfo, Report report) {
        if(Objects.isNull(feedbackInfo)) return;
        report.setInitReportFeedback();
        reportFeedbackHelper.deleteAllReportFeedbacksByReportId(report.getId());
        createReportFeedbackAndSave(feedbackInfo, report);
    }

    private void createReportPricesAndSave(List<CreateReportPriceRequestDto> priceInfo, Report report) {
        priceInfo.stream()
                .map(price -> reportPriceHelper.createReportPriceAndSave(price, report))
                .collect(Collectors.toList());
    }

    private void createReportOptionsAndSave(List<CreateReportOptionRequestDto> optionInfo, Report report) {
        optionInfo.stream()
                .map(option -> reportOptionHelper.createReportOptionAndSave(option, report))
                .collect(Collectors.toList());
    }

    private void createReportFeedbackAndSave(CreateReportFeedbackRequestDto feedbackInfo, Report report) {
        ReportFeedback reportFeedback = reportFeedbackHelper.createReportFeedbackAndSave(feedbackInfo, report);
        report.setReportFeedback(reportFeedback);
    }
}
