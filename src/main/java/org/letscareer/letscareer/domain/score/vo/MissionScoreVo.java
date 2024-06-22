package org.letscareer.letscareer.domain.score.vo;

import lombok.Builder;

@Builder
public record MissionScoreVo(
        Integer th,
        Integer score
) {
}
