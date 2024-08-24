package org.letscareer.letscareer.domain.application.vo;

import org.letscareer.letscareer.domain.application.type.ReportDesiredDateType;
import org.letscareer.letscareer.domain.application.type.ReportFeedbackStatus;

import java.time.LocalDateTime;

public record ReportFeedbackApplicationForAdminVo(
        Long applicationId,
        String name,
        String contactEmail,
        String phoneNumber,
        String wishJob,
        String message,
        ReportFeedbackStatus reportFeedbackStatus,
        String applyFileUrl,
        String recruitmentFileUrl,
        String zoomLink,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        LocalDateTime desiredDateAdmin,
        ReportDesiredDateType desiredDateType,
        Boolean isRefunded,
        LocalDateTime createDate
) {
}
