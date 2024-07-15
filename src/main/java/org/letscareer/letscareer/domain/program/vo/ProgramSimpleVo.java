package org.letscareer.letscareer.domain.program.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record ProgramSimpleVo(
        Long id,
        String title,
        String thumbnail,
        ProgramType programType,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
