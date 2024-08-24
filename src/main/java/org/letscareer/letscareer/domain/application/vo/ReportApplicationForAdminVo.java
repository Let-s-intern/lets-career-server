package org.letscareer.letscareer.domain.application.vo;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;

import java.time.LocalDateTime;

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
        Boolean isRefunded,
        LocalDateTime createDate
) {
}
