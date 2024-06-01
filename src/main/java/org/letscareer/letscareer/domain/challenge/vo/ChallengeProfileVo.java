package org.letscareer.letscareer.domain.challenge.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChallengeProfileVo(
        Long id,
        String title,
        String shortDesc,
        String thumbnail,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline,
        LocalDateTime createDate
) {
}
