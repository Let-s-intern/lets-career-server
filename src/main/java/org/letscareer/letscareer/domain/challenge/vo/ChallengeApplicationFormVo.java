package org.letscareer.letscareer.domain.challenge.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ChallengeApplicationFormVo(
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime deadline
) {
}
