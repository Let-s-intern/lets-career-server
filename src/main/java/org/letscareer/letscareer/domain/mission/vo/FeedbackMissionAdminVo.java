package org.letscareer.letscareer.domain.mission.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FeedbackMissionAdminVo(
        Long id,
        String title,
        Integer th,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Long challengeOptionId,
        String challengeOptionCode
) {
}
