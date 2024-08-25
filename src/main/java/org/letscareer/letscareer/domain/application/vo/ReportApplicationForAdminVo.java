package org.letscareer.letscareer.domain.application.vo;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;
import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;
import org.letscareer.letscareer.domain.report.type.ReportPriceType;

import java.time.LocalDateTime;
import java.util.List;

public record ReportApplicationForAdminVo(
        Long applicationId,
        String name,
        String contactEmail,
        String phoneNumber,

        String wishJob,
        String message,
        ReportApplicationStatus reportApplicationStatus,
        String applyFileUrl,
        String reportFileUrl,
        String recruitmentFileUrl,

        ReportFeedbackStatus reportFeedbackStatus,
        String zoomLink,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        LocalDateTime desiredDateAdmin,
        ReportDesiredDateType desiredDateType,
        LocalDateTime createDate,

        Long paymentId,
        String orderId,
        ReportPriceType reportPriceType,
        String couponTitle,
        Integer finalPrice,
        Boolean isRefunded
) {
}
