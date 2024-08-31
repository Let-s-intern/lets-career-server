package org.letscareer.letscareer.domain.report.dto.req;

import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.time.LocalDateTime;
import java.util.List;

public record CreateReportApplicationRequestDto(
        ReportPriceType reportPriceType,
        List<Long> optionIds,
        Boolean isFeedbackApplied,

        Long couponId,
        String paymentKey,
        String orderId,
        String amount,
        Integer programPrice,
        Integer programDiscount,

        String applyUrl,
        String recruitmentUrl,

        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,

        String wishJob,
        String message
) {
}
