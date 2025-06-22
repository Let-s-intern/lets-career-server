package org.letscareer.letscareer.domain.attendance.vo;

import lombok.Builder;

@Builder
public record FeedbackMissionAttendanceDetailVo(
        Long id,
        String feedback
) {
}
