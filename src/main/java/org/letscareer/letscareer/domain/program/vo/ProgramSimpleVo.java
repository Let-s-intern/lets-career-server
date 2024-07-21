package org.letscareer.letscareer.domain.program.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.live.type.ProgressType;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record ProgramSimpleVo(
        Long id,
        Long applicationId,
        String title,
        String thumbnail,
        ProgramType programType,
        ProgressType progressType,
        Boolean isCanceled,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
