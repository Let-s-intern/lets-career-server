package org.letscareer.letscareer.domain.report.dto.req;

import jakarta.validation.constraints.NotNull;
import org.letscareer.letscareer.domain.faq.dto.request.CreateProgramFaqRequestDto;
import org.letscareer.letscareer.domain.report.type.ReportType;

import java.time.LocalDateTime;
import java.util.List;

public record CreateReportRequestDto(
    @NotNull
    ReportType reportType,
    LocalDateTime visibleDate,
    @NotNull
    String title,
    String contents,
    String notice,
    @NotNull
    List<CreateReportPriceRequestDto> priceInfo,
    @NotNull
    List<CreateReportOptionRequestDto> optionInfo,
    @NotNull
    CreateReportFeedbackRequestDto feedbackInfo,
    @NotNull
    List<CreateProgramFaqRequestDto> faqInfo
) {
}
