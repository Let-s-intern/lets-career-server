package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewAdminVo(
        Long id,
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
