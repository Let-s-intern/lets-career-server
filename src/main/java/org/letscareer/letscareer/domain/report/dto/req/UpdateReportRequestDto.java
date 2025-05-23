package org.letscareer.letscareer.domain.report.dto.req;

import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateReportRequestDto(
    ReportType reportType,
    Boolean isVisible,
    LocalDateTime visibleDate,
    String title,
    String contents,
    String notice,
    List<CreateReportPriceRequestDto> priceInfo,
    List<CreateReportOptionRequestDto> optionInfo,
    CreateReportFeedbackRequestDto feedbackInfo,
    List<CreateProgramFaqRequestDto> faqInfo
) {
}
