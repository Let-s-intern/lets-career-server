package org.letscareer.letscareer.domain.program.dto.request;

import java.time.LocalDateTime;

public record GetProgramsForDurationRequestDto(
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
