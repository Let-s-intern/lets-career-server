package org.letscareer.letscareer.domain.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.faq.entity.Faq;
import org.letscareer.letscareer.domain.faq.helper.FaqHelper;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportFeedbackRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportOptionRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.CreateReportPriceRequestDto;
import org.letscareer.letscareer.domain.report.dto.req.UpdateReportRequestDto;
import org.letscareer.letscareer.domain.report.entity.Report;
import org.letscareer.letscareer.domain.report.entity.ReportFeedback;
import org.letscareer.letscareer.domain.report.helper.ReportHelper;
import org.letscareer.letscareer.domain.report.helper.ReportOptionHelper;
import org.letscareer.letscareer.domain.report.helper.ReportPriceHelper;
import org.letscareer.letscareer.domain.report.service.UpdateReportService;
import org.letscareer.letscareer.domain.user.entity.User;
import org.letscareer.letscareer.domain.user.type.UserRole;
import org.letscareer.letscareer.global.error.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.letscareer.letscareer.domain.report.error.ReportErrorCode.REPORT_CANNOT_UPDATED;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateReportServiceImpl implements UpdateReportService {
    private final ReportHelper reportHelper;
    private final ReportPriceHelper reportPriceHelper;
    private final ReportOptionHelper reportOptionHelper;
    private final FaqHelper faqHelper;

    @Override
    public void execute(User user, Long reportId, UpdateReportRequestDto requestDto) {
        validateAdminRole(user);
        Report report = reportHelper.findReportByReportIdOrThrow(reportId);
        // reportHelper.validateUpdateVisibleDate(requestDto);
        report.updateReport(requestDto);
        updateReportPrices(requestDto.priceInfo(), report);
        updateReportOptions(requestDto.optionInfo(), report);
        updateReportFeedback(requestDto.feedbackInfo(), report);
        updateReportFaqs(requestDto.faqInfo(), report);
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
        ReportFeedback reportFeedback = report.getReportFeedback();
        reportFeedback.updateReportFeedback(feedbackInfo);
    }

    private void updateReportFaqs(List<CreateProgramFaqRequestDto> faqInfo, Report report) {
        if(Objects.isNull(faqInfo)) return;
        faqHelper.deleteReportFaqsByReportId(report.getId());
        report.setInitFaqList();
        createFaqListAndSave(faqInfo, report);
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

    private void validateAdminRole(User currentUser) {
        if(!currentUser.getRole().equals(UserRole.ADMIN)) {
            throw new UnauthorizedException();
        }
    }
}
