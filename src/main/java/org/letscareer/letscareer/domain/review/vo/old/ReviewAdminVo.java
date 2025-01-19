package org.letscareer.letscareer.domain.review.vo.old;

import lombok.Builder;
import org.letscareer.letscareer.domain.program.type.ProgramType;

import java.time.LocalDateTime;

@Builder
public record ReviewAdminVo(
        Long id,
        Long applicationId,
        String programTitle,
        ProgramType programType,
        Long userId,
        String name,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        String programDetail,
        Integer score,
        Boolean isVisible,
        LocalDateTime createdDate
) {
}
