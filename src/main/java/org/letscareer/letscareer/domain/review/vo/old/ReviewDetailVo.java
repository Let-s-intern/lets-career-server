package org.letscareer.letscareer.domain.review.vo.old;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewDetailVo(
        Long id,
        Long applicationId,
        Long userId,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score,
        String programDetail,
        LocalDateTime createdDate
) {
}
