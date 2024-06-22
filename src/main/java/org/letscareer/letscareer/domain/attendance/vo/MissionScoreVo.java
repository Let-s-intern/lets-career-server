package org.letscareer.letscareer.domain.attendance.vo;

import lombok.Builder;

@Builder
public record MissionScoreVo(
        Long missionId,
        Integer th
) {
}
