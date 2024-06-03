package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewDetailVo(
        Long id,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score,
        LocalDateTime createdDate
) {
}
