package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;

@Builder
public record MyMissionFeedbackVo(
        Long id,
        Integer th,
        String title
) {
}
