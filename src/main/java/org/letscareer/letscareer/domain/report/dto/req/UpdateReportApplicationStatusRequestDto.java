package org.letscareer.letscareer.domain.report.dto.req;

import org.letscareer.letscareer.domain.application.type.ReportApplicationStatus;

public record UpdateReportApplicationStatusRequestDto(
        ReportApplicationStatus status
) {
}
