package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportFeedbackRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportOptionRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.helper.ReportFeedbackHelper;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
import org.letscareer.letscareer.domain.report.service.CreateReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateReportServiceImpl implements CreateReportService {
    private final ReportHelper reportHelper;
    private final ReportPriceHelper reportPriceHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final ReportFeedbackHelper reportFeedbackHelper;
    private final FaqHelper faqHelper;

    @Override
    public void execute(CreateReportRequestDto requestDto) {
        Report report = reportHelper.createReportAndSave(requestDto);
        createReportPricesAndSave(requestDto.priceInfo(), report);
        createReportOptionsAndSave(requestDto.optionInfo(), report);
        createReportFeedbackAndSave(requestDto.feedbackInfo(), report);
        createFaqListAndSave(requestDto.faqInfo(), report);
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

    private void createFaqListAndSave(List<CreateProgramFaqRequestDto> requestDtoList,
                                      Report report) {
        List<Faq> faqs = getFaqsById(requestDtoList);
        faqs.stream().forEach(faq -> {
            faqHelper.createFaqReportAndSave(faq, report);
        });
    }

    private List<Faq> getFaqsById(List<CreateProgramFaqRequestDto> requestDtoList) {
        return requestDtoList.stream()
                .map(request -> faqHelper.findFaqByIdAndThrow(request.faqId()))
                .collect(Collectors.toList());
    }
}
