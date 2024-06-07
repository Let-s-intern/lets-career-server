package org.letscareer.letscareer.domain.program.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record ProgramForAdminVo(
        Long id,
        ProgramType programType,
        String title,
        Integer currentCount,
        Integer participationCount,
        String zoomLink,
        String zoomPassword,
        Boolean isVisible,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime beginning,
        LocalDateTime deadline
) {
}
