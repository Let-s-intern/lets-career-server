package org.letscareer.letscareer.domain.review.vo;

import lombok.Builder;

@Builder
public record ReviewVo(
        String name,
        Integer nps,
        String npsAns,
        Boolean npsCheckAns,
        String content,
        Integer score
) {
}
