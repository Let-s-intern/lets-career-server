package org.letscareer.letscareer.domain.report.dto.req;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateMyReportApplicationRequestDto(
        @NotNull
        String applyUrl,
        String recruitmentUrl,
        LocalDateTime desiredDate1,
        LocalDateTime desiredDate2,
        LocalDateTime desiredDate3,
        String wishJob,
        String message
) {
}
