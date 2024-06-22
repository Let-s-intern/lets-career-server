package org.letscareer.letscareer.domain.program.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

@Builder
public record ProgramSimpleVo(
        Long programId,
        ProgramType programType
) {
}
