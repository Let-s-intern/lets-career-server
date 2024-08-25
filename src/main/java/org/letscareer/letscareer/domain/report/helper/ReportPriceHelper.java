package org.letscareer.letscareer.domain.report.helper;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportPrice;
import org.letscareer.letscareer.domain.report.repository.ReportPriceRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportPriceHelper {
    private final ReportPriceRepository reportPriceRepository;

    public ReportPrice createReportPriceAndSave(CreateReportPriceRequestDto requestDto, Report report) {
        ReportPrice reportPrice = ReportPrice.createReportPrice(requestDto, report);
        return reportPriceRepository.save(reportPrice);
    }
}
