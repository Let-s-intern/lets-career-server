package org.letscareer.letscareer.domain.attendance.vo;

import lombok.Builder;

@Builder
public record AttendanceScoreVo(
        Integer th,
        Integer score
) {
}
