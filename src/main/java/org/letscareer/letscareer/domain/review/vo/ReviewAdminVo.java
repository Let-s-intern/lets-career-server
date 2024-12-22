package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record ReviewAdminVo(
        Long id,
        Long applicationId,
        String programTitle,
        ProgramType programType,
        String name,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score,
        String programDetail,
        Boolean isVisible,
        LocalDateTime createdDate
) {
}
