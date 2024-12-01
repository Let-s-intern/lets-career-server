package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record ReviewAdminVo(
        Long id,
        String programTitle,
        ProgramType programType,
        String name,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score,
        Boolean isVisible,
        LocalDateTime createdDate
) {
}
