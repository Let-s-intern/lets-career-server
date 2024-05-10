package org.letscareer.letscareer.domain.program.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record AdminProgramVo(
        Long id,
        ProgramType programType,
        String title,
        Integer currentCount,
        Integer participationCount,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        Boolean isVisible,
        String zoomLink,
        String zoomPassword
) {
}
